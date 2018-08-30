package com.bjsxt.repository;

import com.bjsxt.pojo.Users;

public interface UserRepository {

    Users findUserById(Integer userid);

}
