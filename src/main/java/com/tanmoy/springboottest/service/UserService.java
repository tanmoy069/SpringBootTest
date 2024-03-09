package com.tanmoy.springboottest.service;

import com.tanmoy.springboottest.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    UserDto findById(Long id);
    boolean deleteUser(Long id);
    List<UserDto> findAll();
}
