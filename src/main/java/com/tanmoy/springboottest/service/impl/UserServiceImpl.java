package com.tanmoy.springboottest.service.impl;

import com.tanmoy.springboottest.constant.KafkaTopics;
import com.tanmoy.springboottest.dto.UserDto;
import com.tanmoy.springboottest.entity.User;
import com.tanmoy.springboottest.entity.redis.UserCache;
import com.tanmoy.springboottest.repository.UserRepository;
import com.tanmoy.springboottest.service.KafkaProducerService;
import com.tanmoy.springboottest.service.UserCacheService;
import com.tanmoy.springboottest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserCacheService userCacheService;
    private final KafkaProducerService kafkaProducerService;
    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public UserDto saveUser(UserDto userDto) {
        User user = userDto.toUser();
        try {
            userCacheService.save(UserDto.toUserCache(user));
            user = repository.save(user);
            kafkaProducerService.sendMessage(UserDto.from(user), KafkaTopics.USER_EVENT);
            return UserDto.from(user);
        } catch (Exception e) {
            log.error("Failed to save user due to: " + e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) {
        User user = findUserById(userDto.getUserId());
        userDto.updateUser(user);
        try {
            UserCache userCache = userCacheService.findById(user.getUserId());
            user = repository.save(user);
            if (Objects.nonNull(userCache)) {
                log.info("Updating user info in cache");
                userDto.updateUser(userCache);
                userCacheService.update(userCache);
            } else {
                log.info("Saving user info in cache");
                userCacheService.save(UserDto.toUserCache(user));
            }
            return UserDto.from(repository.save(user));
        } catch (Exception e) {
            log.error("Failed to save user due to: " + e.getMessage());
        }
        return null;
    }

    @Override
    public UserDto findById(Long id) {
        UserCache userCache = userCacheService.findById(id);
        if (Objects.nonNull(userCache)) {
            log.info("User returning from redis cache");
            return UserDto.from(userCache);
        }
        log.info("User returning from database");
        return UserDto.from(findUserById(id));
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        User user = findUserById(id);
        try {
            repository.deleteById(id);
            userCacheService.delete(id);
            return true;
        } catch (Exception e) {
            log.error("Failed to delete user due to: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream().map(UserDto::from).toList();
    }

    private User findUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.warn("No user found with id : " + id);
            new RuntimeException("No user found with id : " + id);
            return null;
        });
    }

    private User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> {
            log.warn("No user found with username : " + username);
            new RuntimeException("No user found with username : " + username);
            return null;
        });
    }
}
