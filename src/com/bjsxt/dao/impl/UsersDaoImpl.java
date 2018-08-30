package com.bjsxt.dao.impl;

import com.bjsxt.pojo.Users;
import com.bjsxt.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

public class UsersDaoImpl implements UserRepository {

    @PersistenceContext(name = "entityManagerFactory")
    private EntityManager em;

    @Override
    public Users findUserById(Integer userid) {
        System.out.println( "myRepository ... " );
        return em.find( Users.class,userid );
    }
}
