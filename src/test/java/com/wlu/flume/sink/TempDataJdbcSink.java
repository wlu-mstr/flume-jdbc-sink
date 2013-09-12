package com.wlu.flume.sink;

import com.wlu.flume.sink.domain.TempData;

public class TempDataJdbcSink extends PojoJdbcSink<TempData> {

	public TempDataJdbcSink() {
		super(TempData.class);
	}

}
