package com.bjsxt.test;

import com.bjsxt.dao.UsersReDao;
import com.bjsxt.pojo.Users;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * repository 接口测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RepositoryTest {

    @Autowired
    private UsersReDao userDao;


    /**
     * 需求：使用用户名作为查询条件
     */
    @Test
    public  void test1(){
        /**
         * 判断相等的条件，有三种表示方式
         * 1，什么都不写，默认就是相等判断
         * 2，Is
         * 3,Equal
         */
        List<Users> list = this.userDao.findByUsernameIs( "赵六" );
        for (Users users : list) {
            System.out.println( users );
        }

    }
    /**
     * 需求：根据用户姓名做like处理
     * like:tiaojian 关键字
     */
    @Test
    public void test2(){
        List<Users> list =  this.userDao.findByUsernameLike("王%");
        for (Users users : list) {
            System.out.println( users );
        }
    }
    /**
     * 需求：查询名称为王五，并且他的年龄大于等于22岁
     *
     */
    @Test
    public void test3(){
        List<Users> list = this.userDao.findByUsernameAndUserageGreaterThanEqual("王五",17);
        for (Users users : list) {
            System.out.println( users );
        }
    }
    ////jpa query 注解查询
    /**
     * 需求：使用用户名作为查询条件
     */
    @Test
    public  void test4(){
        /**
         * 判断相等的条件，有三种表示方式
         * 1，什么都不写，默认就是相等判断
         * 2，Is
         * 3,Equal
         */
        List<Users> list = this.userDao.findByUsernameIsUseJPQL( "王五" );
        for (Users users : list) {
            System.out.println( users );
        }

    }

    /**
     * 需求：根据用户姓名做like处理
     * like:tiaojian 关键字
     */
    @Test
    public void test5(){
        List<Users> list =  this.userDao.findByUsernameLikeJPQL("王%");
        for (Users users : list) {
            System.out.println( users );
        }
    }

    /**
     * 需求：查询名称为王五，并且他的年龄大于等于22岁
     *
     */
    @Test
    public void test6(){
        List<Users> list = this.userDao.findByUsernameAndUserageGreaterThanEqualJPQL("王五",17);
        for (Users users : list) {
            System.out.println( users );
        }
    }

    ////jpa query sql注解查询
    /**
     * 需求：使用用户名作为查询条件
     */
    @Test
    public  void test7(){
        /**
         * 判断相等的条件，有三种表示方式
         * 1，什么都不写，默认就是相等判断
         * 2，Is
         * 3,Equal
         */
        List<Users> list = this.userDao.findByUsernameIsUseSQL( "王五" );
        for (Users users : list) {
            System.out.println( users );
        }

    }

    /**
     * 需求：根据用户姓名做like处理
     * like:tiaojian 关键字
     */
    @Test
    public void test8(){
        List<Users> list =  this.userDao.findByUsernameLikeSQL("王%");
        for (Users users : list) {
            System.out.println( users );
        }
    }

    /**
     * 需求：查询名称为王五，并且他的年龄大于等于22岁
     *
     */
    @Test
    public void test9(){
        List<Users> list = this.userDao.findByUsernameAndUserageGreaterThanEqualSQL("王五",17);
        for (Users users : list) {
            System.out.println( users );
        }
    }
    /////更新操作测试
    @Test
    @Transactional
    @Rollback(false)
    public void testUpdate(){
        this.userDao.updateUserAgeById( 888,6 );
    }
}
