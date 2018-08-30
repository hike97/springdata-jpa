package com.bjsxt.test;


import com.bjsxt.dao.UsersDao;
import com.bjsxt.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Log4jConfigurer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileNotFoundException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UsersDaoImplTest {

	static {
		try {
			Log4jConfigurer.initLogging("classpath:com/config/log4j.properties");
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot Initialize log4j");
		}
	}

	@Autowired
	private UsersDao usersDao;

	@Test
	@Transactional
	@Rollback(false)
	public void testInsertUsers(){
		Users users = new Users();
		users.setUserage(29);
		users.setUsername("xiaosunyang");
		usersDao.save(users);
	}

	@PersistenceContext(name = "entityManagerFactory")
	private EntityManager entityManager;
	@Test
	public void test1(){
		System.out.println(this.usersDao);
		//结果：org.springframework.data.jpa.repository.support.SimpleJpaRepository@3b6c624
		System.out.println(this.usersDao.getClass());
		//结果：class com.sun.proxy.$Proxy30

		//实现原理 jpa工厂代理生成接口的实现类
		// getRepository(UsersDao.class); 实现类为simpleJpaRepository的对象
		//要求 该接口必须要继承repository接口
		JpaRepositoryFactory factory = new JpaRepositoryFactory(entityManager);
		UsersDao ud = factory.getRepository(UsersDao.class);
		System.out.println("ud:"+ud);
		System.out.println("ud_Class:"+ud.getClass());
	}
	
}
