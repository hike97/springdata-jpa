package com.bjsxt.dao;

import com.bjsxt.pojo.Users;
import com.bjsxt.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


/**
 * jpaspecificationexecutor 不能单独使用，
 * 需要配合着jpa中的其他接口一起使用
 * 要产生simplejparepository ，需要实现repository接口
 * jpaspecificationexecutor 没有实现repository接口
 * 所以为了通过simplejparepository 实现代理 就必须和其他继承过repository接口
 *  的其他repository 共同使用
 * * */
public interface UsersDao extends JpaRepository<Users,Integer> ,JpaSpecificationExecutor<Users>,UserRepository{


}
