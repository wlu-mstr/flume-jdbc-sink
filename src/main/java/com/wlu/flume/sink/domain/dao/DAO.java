package com.wlu.flume.sink.domain.dao;

public abstract class DAO<T> {
	private String namespace;
	public abstract void insert(T instance);
}
