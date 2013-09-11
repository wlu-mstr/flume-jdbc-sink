package com.wlu.flume.source;

import java.io.IOException;
import java.net.UnknownHostException;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.wlu.flume.sink.domain.TempData;

public class TestSocketSource {
	public static void main(String args[]) {
		SocketDataSource src;
		try {
			src = new SocketDataSource("localhost", 44444);
			TempData value;
			byte[] protobuf;

			value = new TempData("2013 9-9, 1:30", 50);
			Schema<TempData> schema = RuntimeSchema.getSchema(TempData.class);
			protobuf = ProtobufIOUtil.toByteArray(value, schema,
					LinkedBuffer.allocate(1024));
			src.sendData("hhhhhhhhhhhhhhhhhhhhhhhh".getBytes());
			src.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
