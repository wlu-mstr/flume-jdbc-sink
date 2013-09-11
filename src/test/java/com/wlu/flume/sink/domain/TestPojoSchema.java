package com.wlu.flume.sink.domain;

import org.junit.Test;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class TestPojoSchema {
	
	@Test
	public void test1(){
		Schema<Contact> schema = RuntimeSchema.getSchema(Contact.class);
		Contact contact;
		byte[] protobuf;
		Contact contact2;

		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {

			contact = new Contact(i, "name", "124"+i, "xxx@123.com");

			protobuf = ProtobufIOUtil.toByteArray(contact, schema,
					LinkedBuffer.allocate(1024));

			contact2 = new Contact();
			ProtobufIOUtil.mergeFrom(protobuf, contact2, schema);
			
			org.junit.Assert.assertEquals(contact, contact2);

		}

		long end = System.currentTimeMillis();
		
		System.out.print(end - start);

	}
}
