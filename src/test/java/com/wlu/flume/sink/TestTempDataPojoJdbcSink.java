package com.wlu.flume.sink;

import org.apache.flume.EventDeliveryException;
import org.junit.Test;

import com.wlu.flume.sink.domain.Contact;
import com.wlu.flume.sink.domain.TempData;

public class TestTempDataPojoJdbcSink {
	public void testProcess(){
		TempDataJdbcSink sink = new TempDataJdbcSink();
		sink.setChannel(new TempDataChannelMock());
		try {
			sink.start();
			sink.process();
		} catch (EventDeliveryException e) {
			e.printStackTrace();
		}
	}
}
