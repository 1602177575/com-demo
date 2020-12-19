package com.smart.security.service;

import com.smart.security.dto.UserDto;

public interface UserService {

    UserDto selectUserByName(String username);
}
