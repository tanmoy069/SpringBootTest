package com.tanmoy.springboottest.service;

import com.tanmoy.springboottest.dto.UserDto;

public interface UserService {

    UserDto saveUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    UserDto findById(Long id);
    UserDto findByUsername(String username);
    boolean deleteUser(Long id);
}
