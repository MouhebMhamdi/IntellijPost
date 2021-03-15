package net.javaguides.springboot.springsecurity.Dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.springsecurity.model.Archive;

@Repository
public class ArchiveDAOImpl implements ArchiveDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Archive> get() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Archive> query = currentSession.createQuery("from Archive", Archive.class);
		List<Archive> list = query.getResultList();
		return list;
	}

	@Override
	public Archive get(long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Archive archiveObj = currentSession.get(Archive.class, id);
		return archiveObj;
	}
	public Archive empGet(int employe) {
		Session currentSession = entityManager.unwrap(Session.class);
		Archive archiveObj = currentSession.get(Archive.class, employe);
		return archiveObj;
	}
	@Override
	public void save(Archive archive) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(archive);
	}

	@Override
	public void delete(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Archive archiveObj = currentSession.get(Archive.class, id);
		currentSession.delete(archiveObj);
	}

	@Override
	public void insertEmp(long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		int arch=currentSession.createQuery("insert into Archive(employe_id) select id from Employe where id= "+id).executeUpdate();
		
	}

}
