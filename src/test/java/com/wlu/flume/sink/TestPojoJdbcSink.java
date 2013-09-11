package com.wlu.flume.sink;

import org.apache.flume.EventDeliveryException;
import org.junit.Test;

import com.wlu.flume.sink.domain.Contact;

public class TestPojoJdbcSink {
	@Test
	public void testProcess(){
		PojoJdbcSink<Contact> sink = new PojoJdbcSink<Contact>(Contact.class);
		sink.setChannel(new ChannelMock());
		try {
			sink.start();
			sink.process();
		} catch (EventDeliveryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
