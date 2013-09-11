package com.wlu.flume.sink.domain.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.wlu.flume.sink.domain.TempData;

public class TempDataDAO extends DAO<TempData> {

	private SqlSessionFactory sqlSessionFactory;

	public TempDataDAO() {
		sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
	}

	@Override
	public void insert(TempData instance) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();

		try {
			session.insert("TempData.insert", instance);
			session.commit();
		} finally {
			session.close();
		}
	}

}
