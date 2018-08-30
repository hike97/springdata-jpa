package com.bjsxt.dao;

import com.bjsxt.pojo.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface Users_page_Dao extends PagingAndSortingRepository<Users,Integer> {



}
