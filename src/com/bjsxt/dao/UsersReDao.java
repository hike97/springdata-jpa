package com.bjsxt.dao;

import com.bjsxt.pojo.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import sun.awt.SunHints;

import java.util.List;

public interface UsersReDao extends Repository<Users,Integer> {

    //方法命名规则
    List<Users> findByUsernameIs(String string);

    List<Users> findByUsernameLike(String s);

    List<Users> findByUsernameAndUserageGreaterThanEqual(String s, int i);

    //使用@Query注解查询
    @Query(value = "from Users where username = ?")
    List<Users> findByUsernameIsUseJPQL(String name);

    @Query("from Users where username like ?")
    List<Users> findByUsernameLikeJPQL(String s);

    @Query("from Users where username = ? and userage >=?")
    List<Users> findByUsernameAndUserageGreaterThanEqualJPQL(String s, int i);

    //使用Query注解查询SQL
    @Query(value = "select * from t_users where username = ?",nativeQuery = true)
    List<Users> findByUsernameIsUseSQL(String name);

    @Query(value = "select * from t_users where username like ?",nativeQuery = true)
    List<Users> findByUsernameLikeSQL(String s);

    @Query(value = "select * from t_users where username = ? AND userage >= ?",nativeQuery = true)
    List<Users> findByUsernameAndUserageGreaterThanEqualSQL(String s, int i);

    //更新操作
    @Query("update Users set userage = ? where userid = ?")
    @Modifying
    void updateUserAgeById(Integer age , Integer id);
}
