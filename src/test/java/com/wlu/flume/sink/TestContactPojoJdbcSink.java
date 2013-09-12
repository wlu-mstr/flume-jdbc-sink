package com.wlu.flume.sink;

import org.apache.flume.EventDeliveryException;
import org.junit.Test;

import com.wlu.flume.sink.domain.Contact;
import com.wlu.flume.sink.domain.TempData;

public class TestContactPojoJdbcSink {
	public void testProcess(){
		PojoJdbcSink<TempData> sink = new PojoJdbcSink<TempData>(TempData.class);
		sink.setChannel(new TempDataChannelMock());
		try {
			sink.start();
			sink.process();
		} catch (EventDeliveryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
