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
import com.wlu.flume.sink.domain.Contact;

public class ContactChannelMock extends MemoryChannel {

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
		Schema<Contact> schema = RuntimeSchema.getSchema(Contact.class);
		Contact contact;
		byte[] protobuf;

		contact = new Contact(1, "name", "124", "xxx@123.com");

		protobuf = ProtobufIOUtil.toByteArray(contact, schema,
				LinkedBuffer.allocate(1024));

		SimpleEvent e = new SimpleEvent();
		e.setBody(protobuf);
		return e;
	}

}
