package com.smart.auth.Service;

import com.smart.auth.entity.User;

public interface UserService {

    User findUserByName(String name);
}
