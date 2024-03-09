package com.tanmoy.springboottest.service.impl;

import com.tanmoy.springboottest.dto.UserDto;
import com.tanmoy.springboottest.entity.User;
import com.tanmoy.springboottest.repository.UserRepository;
import com.tanmoy.springboottest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userDto.toUser();
        try {
            return UserDto.from(repository.save(user));
        } catch (Exception e) {
            log.error("Failed to save user due to: " + e.getMessage());
        }
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userDto.toUser();
        try {
            return UserDto.from(repository.save(user));
        } catch (Exception e) {
            log.error("Failed to save user due to: " + e.getMessage());
        }
        return null;
    }

    @Override
    public UserDto findById(Long id) {
        return UserDto.from(findUserById(id));
    }

    @Override
    public UserDto findByUsername(String username) {
        return UserDto.from(repository.findByUsername(username).orElseThrow(() -> {
            log.warn("No user found with username : " + username);
            new RuntimeException("No user found with username : " + username);
            return null;
        }));
    }

    @Override
    public boolean deleteUser(Long id) {
        User user = findUserById(id);
        return false;
    }

    private User findUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.warn("No user found with id : " + id);
            new RuntimeException("No user found with id : " + id);
            return null;
        });
    }
}
