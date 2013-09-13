steps of using PojoJdbcSink
=======================================
###About the project:
	
- Send java pojo through Flume (NetCat as source) and data will be stored to Database (a special Sink). 
- The porject uses Protostuff (svn2github/protostuff) for pojo SerDe; mybatis (mybatis/mybatis-3) for ORM. 
	
###Framework as below:	


```
              +------------+
 java pojo +->|protostuff  |
              +------+-----+
                     |
                     v
                +-------------+   +----------+      +--------------+
                |Netcat source+-->|channel   |+---> |pojo jdbc sink|
                +-------------+   +----------+      +--------------+
                                                            +
                                                            |            +----------+
                                                     +------v-----+      |          |
                                                     | Mybatis    |+---->|----------|
                                                     +------------+      | Database |
                                                                         |          |
                                                                         +----------+
                                                                         
```                                                                       

###Steps of using the project:

1. design com.package.pojo with constructor, getter, setter function;
2. design com.package.dao.pojoDAO
	2.1 in sqlMapConfig, add Alias, add xxx-map.xml path to sqlMapConfig;
	2.2 in xxx-map.xml, add mapping from API to sql
	2.3 implement pojoDAO extends DAO<pojo> Only 'insert' is needed here
3. Create a Flume JdbcSink instance for the pojo:
	PojoJdbcSink<pojo> sink = new PojoJdbcSink<pojo>(pojo.class);
   Or create a Flume JdbcSink class for the pojo:
   	class PojoJdbcSink extends PojoJdbcSink<Pojo>

###
Notes:

- We should change NetCatSource to BytesNetCatSource for Application, and the reason is:
 NetCatSource use '\n' as line end identification, but there could be some 0A byte in result of serialization of pojo. BytesNetCatSource does not use any identification so each package is used to deserialize one single pojo.
	
- com.wlu.flume.source.TestSocketSource is a demo of data sender. It send bytes (serialized bytes of a pojo) to ByteNetCatSource's host:port through Socket;
	
- an example of Temperate data is added: com.wlu.flume.sink.TempDataJdbcSink.
	
	
