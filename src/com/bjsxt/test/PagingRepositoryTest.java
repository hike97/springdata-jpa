package com.bjsxt.test;

import com.bjsxt.dao.Users_CRUD_Dao;
import com.bjsxt.dao.Users_page_Dao;
import com.bjsxt.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * repository 接口测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PagingRepositoryTest {

    @Autowired
    private Users_page_Dao userDao;

    /**
     * 分页查询
     */
    @Test
    public  void  test1(){
        int page = 2 ;//当前页的索引，注意索引都是从0开始的。
        int size = 3 ;//size:每页显示3条数据
        Pageable pageable = new PageRequest( page, size );
        Page<Users> p = this.userDao.findAll( pageable );
        System.out.println("数据的总条数："+ p.getTotalElements() );
        System.out.println( "总页数：" + p.getTotalPages() );
        List<Users> users = p.getContent();
        for (Users user : users) {
            System.out.println( user );
        }
    }
    /**
     * 对单列做排序处理
     */
    @Test
    public void test2(){
        //sort 该对象封装了排序规则以及指定的排序字段（对象的属性里表示）
        //direction:排序规则
        //properties:制定做排序的属性
        Sort sort = new Sort( Sort.Direction.DESC,"userid" );
        List<Users> list = (List<Users>) this.userDao.findAll( sort );
        for (Users users : list) {
            System.out.println( users );
        }
    }
    /**
     * 对多列做排序处理
     */
    @Test
    public void test3(){
        //sort 该对象封装了排序规则以及指定的排序字段（对象的属性里表示）
        //direction:排序规则
        //properties:指定做排序的属性
        Sort.Order order = new Sort.Order( Sort.Direction.DESC, "userage" );
        Sort.Order order1 = new Sort.Order( Sort.Direction.ASC, "username" );
        Sort sort = new Sort( order,order1 );
        List<Users> list = (List<Users>) this.userDao.findAll( sort );
        for (Users users : list) {
            System.out.println( users );
        }
    }

}
