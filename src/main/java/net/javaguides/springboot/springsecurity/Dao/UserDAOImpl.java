package net.javaguides.springboot.springsecurity.Dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.springsecurity.model.User;
@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<User> get() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> query = currentSession.createQuery("from User", User.class);
		List<User> list = query.getResultList();
		return list;
	}

	@Override
	public User get(long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		User userObj = currentSession.get(User.class, id);
		return userObj;
	}

	@Override
	public void delete(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		User userObj = currentSession.get(User.class, id);
		currentSession.delete(userObj);
		
	}

}
