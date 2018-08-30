package com.bjsxt.dao;

import com.bjsxt.pojo.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface Users_CRUD_Dao extends CrudRepository<Users,Integer> {



}
