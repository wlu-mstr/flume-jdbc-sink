		ʹ��PojoJdbcSink�Ĳ���
1. ���com.package.pojo�����캯����getter��setter������
2. ���com.package.dao.pojoDAO
	2.1 ��sqlMapConfig�����Alias����mappers������µ���Դxxx-map.xml;
	2.2 ��xxx-map.xml�����API��sql֮���ӳ�䣻
	2.3 ʵ��pojoDAO extends DAO<pojo>������ֻ��Ҫʵ��insert����
3. Ŀǰʹ��unit test���ԣ�
	PojoJdbcSink<pojo> sink = new PojoJdbcSink<pojo>(pojo.class);
