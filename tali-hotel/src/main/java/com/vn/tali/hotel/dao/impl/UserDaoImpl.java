//package com.vn.tali.hotel.dao.impl;
//
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.vn.tali.hotel.dao.AbstractDao;
//import com.vn.tali.hotel.dao.UserDao;
//import com.vn.tali.hotel.entity.Room;
//import com.vn.tali.hotel.entity.User;
//
//@Repository("userDao")
//@Transactional
//public class UserDaoImpl extends AbstractDao<Integer, Room> implements UserDao {
//
//	@Override
//	public User findByPhone(String phone) {
//		return (User) this.getSession().createCriteria(User.class).add(Restrictions.eq("phone", phone))
//				.list().stream().findFirst().orElse(null);
//	}
//
//
//
//
//}
