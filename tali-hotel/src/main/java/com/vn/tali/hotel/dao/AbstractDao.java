package com.vn.tali.hotel.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vn.tali.hotel.entity.BaseEntity;

@Repository
public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		Session session = this.sessionFactory.openSession();
//		Session session = this.sessionFactory.getCurrentSession();
		return session;
	}

	protected CriteriaBuilder getBuilder() {
		return this.getSession().getCriteriaBuilder();
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void update(T entity) {
		executeInTransaction(session -> {
			session.update(entity);
			session.flush();
		});
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public void update(BaseEntity entity) {
		this.getSession().update(entity);
	}

	public void merge(BaseEntity entity) {
		this.getSession().merge(entity);
	}

	public void delete(BaseEntity entity) {
		this.getSession().delete(entity);
	}

	public void refresh(BaseEntity entity) {
		this.getSession().refresh(entity);
	}

	public void flush() {
		this.getSession().flush();
	}

	protected void executeInTransaction(Consumer<Session> action) {
		Session session = getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			action.accept(session);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings({ "hiding" })
	protected <T> T executeInSession(Function<Session, T> action) throws Exception {
	    Session session = getSession();
	    try {
	        return action.apply(session);
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        session.close(); // Đóng session sau khi hoàn thành tác vụ
	    }
	}

	
}
