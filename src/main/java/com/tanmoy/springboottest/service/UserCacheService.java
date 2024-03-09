package com.tanmoy.springboottest.service;

import com.tanmoy.springboottest.entity.redis.UserCache;

import java.util.Map;

public interface UserCacheService {

    void save(UserCache userCache);
    void update(UserCache userCache);
    void delete(Long id);
    UserCache findById(Long id);
    Map<Long, UserCache> getAll();
}
