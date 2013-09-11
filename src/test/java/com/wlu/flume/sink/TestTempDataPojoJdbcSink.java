package com.wlu.flume.sink;

import org.apache.flume.EventDeliveryException;
import org.junit.Test;

import com.wlu.flume.sink.domain.Contact;

public class TestTempDataPojoJdbcSink {
	@Test
	public void testProcess(){
		PojoJdbcSink<Contact> sink = new PojoJdbcSink<Contact>(Contact.class);
		sink.setChannel(new ContactChannelMock());
		try {
			sink.start();
			sink.process();
		} catch (EventDeliveryException e) {
			e.printStackTrace();
		}
	}
}
