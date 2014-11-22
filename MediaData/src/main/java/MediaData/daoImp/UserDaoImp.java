package MediaData.daoImp;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import MediaData.dao.UserDao;
import MediaData.entity.User;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

	static Logger log = Logger.getLogger(UserDaoImp.class);
	private SessionFactory sessionFactory;

	@Autowired
	public UserDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otv.dao.UserDao#addUser(com.otv.UserEntity.User)
	 */
	@Override
	public void addUser(User user) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(user);					
		} catch (Exception e) {
			log.error("e.getStackTrace():"+e.getStackTrace());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otv.dao.UserDao#getUser(java.lang.String)
	 */
	@Override
	public List<User> getUsers(String userName) {

		log.info("can you see me?-->" + userName);

		Session session = sessionFactory.getCurrentSession();

		List<User> userList = (List<User>) session
				.createQuery("select p from User p where p.userName like :pUserName")
				.setParameter("pUserName", "%"+userName+"%").list();

		return userList;
	}
	
	@Override
	public List<User> getUsers() {

		Session session = sessionFactory.getCurrentSession();

		List<User> userList = (List<User>) session
				.createQuery("select p from User p").list();

		return userList;
	}

}
