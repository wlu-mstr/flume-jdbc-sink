package com.wlu.flume.sink;

import org.apache.flume.Channel;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Transaction;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.wlu.flume.sink.domain.dao.DAO;

/**
 * 
 * @author wlu This sink will get byte[] from Event and construct the byte into
 *         a Pojo object. Then the object is written to database throught JDBC.
 * 
 */
public class PojoJdbcSink<T> extends AbstractSink {
	private Class<T> pojoClass;
	private Class<DAO<T>> pojoDaoClass;
	private Schema<T> schema;

	private T pojo;
	private DAO<T> pojodao;

	private static final Logger logger = LoggerFactory
			.getLogger(PojoJdbcSink.class);

	public PojoJdbcSink(Class<T> clazz) {
		this.pojoClass = clazz;
		try {
			this.pojoDaoClass = (Class<DAO<T>>) Class.forName(pojoClass
					.getPackage().getName()
					+ ".dao."
					+ pojoClass.getSimpleName() + "DAO");
		} catch (ClassNotFoundException e) {
			logger.error(e.toString());
		}
	}

	@Override
	public synchronized void start() {
		// setup the schema
		schema = RuntimeSchema.getSchema(pojoClass);
		try {
			pojo = pojoClass.newInstance();
			pojodao = pojoDaoClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Status process() throws EventDeliveryException {
		Status result = Status.READY;
		Channel channel = getChannel();
		Transaction transaction = channel.getTransaction();
		Event event = null;

		byte[] payload;

		try {
			transaction.begin();
			event = channel.take();

			if (event != null) {
				payload = event.getBody();

				ProtobufIOUtil.mergeFrom(payload, pojo, schema);
				pojodao.insert(pojo);
			} else {
				// No event found, request back-off semantics from the sink
				// runner
				result = Status.BACKOFF;
			}
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			throw new EventDeliveryException("Failed to log event: " + event,
					ex);
		} finally {
			transaction.close();
		}

		return result;
	}

}
