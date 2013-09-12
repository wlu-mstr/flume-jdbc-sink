package com.wlu.flume.source;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.commons.io.HexDump;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.wlu.flume.sink.domain.TempData;

public class TestSocketSource {
	public static void main(String args[]) {
		SocketDataSource src;
		try {
			
			TempData value;
			byte[] protobuf;

			value = new TempData("2013, 10-10, 10:23", 50);
			Schema<TempData> schema = RuntimeSchema.getSchema(TempData.class);
			protobuf = ProtobufIOUtil.toByteArray(value, schema,
					LinkedBuffer.allocate(1024));
//			byte[] data = new byte[protobuf.length + 1];
//			System.arraycopy(protobuf, 0, data, 0, protobuf.length);
//			data[protobuf.length] = '\n';
			HexDump.dump(protobuf, 0, System.out, 0);
			
			
			src = new SocketDataSource("localhost", 44444);
			src.sendData(protobuf);
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
