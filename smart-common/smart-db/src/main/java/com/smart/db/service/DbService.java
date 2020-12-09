package com.smart.db.service;

import com.smart.db.entity.User;

public interface DbService {

    User selectUserById(Integer id);
}
