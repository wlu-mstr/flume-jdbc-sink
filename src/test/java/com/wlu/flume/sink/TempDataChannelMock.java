package com.wlu.flume.sink;

import org.apache.flume.ChannelException;
import org.apache.flume.Event;
import org.apache.flume.channel.BasicTransactionSemantics;
import org.apache.flume.channel.MemoryChannel;
import org.apache.flume.event.SimpleEvent;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.wlu.flume.sink.domain.TempData;

public class TempDataChannelMock extends MemoryChannel{
	class xxx extends BasicTransactionSemantics {

		@Override
		protected void doPut(Event event) throws InterruptedException {
			// TODO Auto-generated method stub

		}

		@Override
		protected Event doTake() throws InterruptedException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void doCommit() throws InterruptedException {
			// TODO Auto-generated method stub

		}

		@Override
		protected void doRollback() throws InterruptedException {
			// TODO Auto-generated method stub

		}

	}

	@Override
	protected BasicTransactionSemantics createTransaction() {
		return new xxx();
	}

	@Override
	public Event take() throws ChannelException {
		Schema<TempData> schema = RuntimeSchema.getSchema(TempData.class);
		TempData value;
		byte[] protobuf;

		value = new TempData("2013 9-9, 1:30", 36.9);

		protobuf = ProtobufIOUtil.toByteArray(value, schema,
				LinkedBuffer.allocate(1024));

		SimpleEvent e = new SimpleEvent();
		e.setBody(protobuf);
		return e;
	}
}
