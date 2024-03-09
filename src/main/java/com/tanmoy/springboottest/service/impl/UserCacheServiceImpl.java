package com.tanmoy.springboottest.service.impl;

import com.tanmoy.springboottest.entity.redis.UserCache;
import com.tanmoy.springboottest.service.UserCacheService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserCacheServiceImpl implements UserCacheService {

    @Resource(name = "redisTemplate")
    private HashOperations<String, Long, UserCache> hashOperations;

    private final String hashReference = "UserCache";

    @Override
    public void save(UserCache userCache) {
        hashOperations.putIfAbsent(hashReference, (long) userCache.getUserId(), userCache);
    }

    @Override
    public void update(UserCache userCache) {
        hashOperations.put(hashReference, (long) userCache.getUserId(), userCache);
    }

    @Override
    public void delete(Long id) {
        hashOperations.delete(hashReference, 1);
    }

    @Override
    public UserCache findById(Long id) {
        return hashOperations.get(hashReference, id);
    }

    @Override
    public Map<Long, UserCache> getAll() {
        return hashOperations.entries(hashReference);
    }
}
