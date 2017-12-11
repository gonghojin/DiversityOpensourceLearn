package com.websystique.springmvc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDao<PK extends Serializable, T> {
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	                                                           //구현 클래스            // 추상클래스                                //추상클래스 2번째 제너릭 값 = T
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected  Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}
	
	@SuppressWarnings("unchecked")
	public T getByKey(PK key){
		return (T) getSession().get(persistentClass, key);
	}
	
	public void persist(T entity){
		getSession().persist(entity);
	}
	
	public void delete(T entity) {
	    getSession().delete(entity);
	 }

}
