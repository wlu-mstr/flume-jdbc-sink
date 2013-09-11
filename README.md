		使用PojoJdbcSink的步骤
1. 设计com.package.pojo，构造函数，getter和setter函数；
2. 设计com.package.dao.pojoDAO
	2.1 在sqlMapConfig总添加Alias，在mappers中添加新的资源xxx-map.xml;
	2.2 在xxx-map.xml中添加API与sql之间的映射；
	2.3 实现pojoDAO extends DAO<pojo>。这里只需要实现insert功能
3. 目前使用unit test测试：
	PojoJdbcSink<pojo> sink = new PojoJdbcSink<pojo>(pojo.class);
