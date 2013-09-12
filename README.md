		steps of using PojoJdbcSink
Purpose of the project:
	
	Send java pojo through Flume and data will be stored to Database.


1. design com.package.pojo with constructor, getter, setter function;
2. design com.package.dao.pojoDAO
	2.1 in sqlMapConfig, add Alias, add xxx-map.xml path to sqlMapConfig;
	2.2 in xxx-map.xml, add mapping from API to sql
	2.3 implement pojoDAO extends DAO<pojo> Only 'insert' is needed here
3. currently use unit test to validate the logics:
	PojoJdbcSink<pojo> sink = new PojoJdbcSink<pojo>(pojo.class);

change NetCatSource to BytesNetCatSource, and the reason is:
	NetCatSource use '\n' as line end identification, but there could be some 0A byte in result of serialization of pojo. BytesNetCatSource does not use any identification so each package is used to deserialize one single pojo.

Notes:
	com.wlu.flume.source.TestSocketSource is a demo of data sender. It send bytes (serialized bytes of a pojo) to ByteNetCatSource's host:port through Socket;
	an example of Temperate data is added: com.wlu.flume.sink.TempDataJdbcSink.
