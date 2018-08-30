package com.bjsxt.test;

import com.bjsxt.dao.UsersReDao;
import com.bjsxt.dao.Users_CRUD_Dao;
import com.bjsxt.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * repository 接口测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CRUDRepositoryTest {

    @Autowired
    private Users_CRUD_Dao userDao;

    /*
    添加单挑数据
     */
    @Test
    public  void  test1(){
        Users users = new Users();
        users.setUsername( "dashabi" );
        users.setUserage( 99 );
        this.userDao.save( users );
    }

    /**
     * 批量添加
     */
    @Test
    public  void  test2(){
        Users users1 = new Users();
        users1.setUsername( "dashabi1" );
        users1.setUserage( 911 );
        Users users2 = new Users();
        users2.setUsername( "dashabi2" );
        users2.setUserage( 119 );
        List<Users> users = new ArrayList<>();
        users.add( users1 );
        users.add( users2 );
        this.userDao.save( users );
    }
    /**
     * 查询单条
     */
    @Test
    public  void  test3(){
        Users one = this.userDao.findOne( 10 );
        System.out.println( one );
    }
    /**
     * 查询全部
     */
    @Test
    public  void  test4(){
       List<Users> users = (List<Users>) this.userDao.findAll();
        for (Users user : users) {
            System.out.println( user );
        }


    }
    /**
     * 删除
     */
    @Test
    public void test5(){
        this.userDao.delete( 10 );
    }
    /**
     * 更新 方式一
     */
    @Test
    public void test6(){
        Users one = this.userDao.findOne( 9 );
        one.setUsername( "サクラ" );
        this.userDao.save( one );
    }
    /**
     * 更新 方式二
     */
    @Test
    @Transactional
    @Rollback(false)
    public void test7(){
        Users one = this.userDao.findOne( 8 );//持久化状态的
        one.setUsername("渦巻なると,わたぃ");
    }
}
