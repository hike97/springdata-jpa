package com.bjsxt.test;

import com.bjsxt.dao.UsersDao;
import com.bjsxt.dao.Users_CRUD_Dao;
import com.bjsxt.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * repository 接口测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JPARepositoryTest {

    @Autowired
    private UsersDao userDao;
    /**
     * 自定义repository
     */
    @Test
    public void testMyre(){

        Users userById = userDao.findUserById( 14 );

        System.out.println( userById );
    }

    /**
     * 需求：按照用户姓名查询数据
     */
    @Test
    public void test1() {
        Specification<Users> tSpecification = new Specification<Users>() {
            /**
             * @param root 根对象 封装了查询条件的对象
             * @param criteriaQuery 定义了一个基本的查询。一般不使用
             * @param criteriaBuilder 创建一个查询条件
             * @return predicate 定义了查询条件
             */
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.equal( root.get( "username" ), "王五" );
                return predicate;
            }
        };
        List<Users> list = userDao.findAll( tSpecification );
        for (Users users : list) {
            System.out.println( users );
        }
    }
    //多条件查询
    /**
     * 方式一
     * 需求：使用用户姓名以及年龄查询数据
     */
    @Test
    public void test2(){
        //匿名内部类
        Specification<Users> specification = new Specification<Users>() {
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                list.add( criteriaBuilder.equal( root.get( "username" ),"王五" ) );
                list.add( criteriaBuilder.equal( root.get( "userage" ),24 ) );
                //转换数组作为参数
                Predicate[] arr = new Predicate[list.size()];
                Predicate predicate = criteriaBuilder.or( list.toArray( arr ) );
                return predicate;
            }
        };

        List<Users> list = this.userDao.findAll( specification );
        for (Users users : list) {
            System.out.println( users );
        }
    }
    /**
     * 方式二
     * 需求：使用用户姓名以及年龄查询数据
     */
    @Test
    public void test3(){
        //匿名内部类
        Specification<Users> specification = new Specification<Users>() {
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.or( criteriaBuilder.equal(root.get( "username" ),"王五"),criteriaBuilder.equal(root.get( "userage" ),17) );
            }
        };

        List<Users> list = this.userDao.findAll( specification );
        for (Users users : list) {
            System.out.println( users );
        }
    }

    /**
     * 需求：查询王性用户，并且做分页处理
     */
    @Test
    public void test4(){
        //条件
        Specification<Users> spec = new Specification<Users>() {
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like( root.get( "username" ),"王%" );
            }
        };
        //分页
        Pageable pageable =  new PageRequest( 0,2 );
        Page<Users> page = userDao.findAll( spec, pageable );
        System.out.println( "总条数：" + page.getTotalElements() );
        System.out.println( "总页数：" + page.getTotalPages() );
        List<Users> users = page.getContent();
        for (Users user : users) {
            System.out.println( user );
        }
    }
    /**
     * 需求：查询数据库中王姓的用户 并且根据用户id desc
     */
    @Test
    public void test5(){
        //条件
        Specification<Users> spec = new Specification<Users>() {
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like( root.get( "username" ),"王%" );
            }
        };
        //排序
        Sort sort = new Sort( Sort.Direction.DESC, "userid" );
        List<Users> users = userDao.findAll( spec, sort );
        for (Users user : users) {
            System.out.println( user );
        }
    }

    /**
     * 条件 分页 排序
     */
    @Test
    public void test6(){
        //排序定义
        Sort sort = new Sort( Sort.Direction.DESC, "userid" );
        Pageable pageable = new PageRequest( 0,2,sort );

        //查询条件
        Specification<Users> spec = new Specification<Users>() {
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like( root.get( "username" ),"王%" );
            }
        };
        //分页
        Page<Users> page = userDao.findAll( spec, pageable );
        System.out.println( "总条数：" + page.getTotalElements() );
        System.out.println( "总页数：" + page.getTotalPages() );
        List<Users> users = page.getContent();
        for (Users user : users) {
            System.out.println( user );
        }

    }
}
