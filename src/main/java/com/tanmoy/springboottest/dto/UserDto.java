package com.tanmoy.springboottest.dto;

import com.tanmoy.springboottest.entity.User;
import com.tanmoy.springboottest.entity.redis.UserCache;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserDto {

    private int userId;
    private String username;
    private String password;
    private boolean isActive;
    private String firstName;
    private String lastName;

    public static UserDto from(User user) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    public static UserDto from(UserCache userCache) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(userCache, dto);
        return dto;
    }

    public static UserCache toUserCache(User user) {
        UserCache userCache = new UserCache();
        BeanUtils.copyProperties(user, userCache);
        return userCache;
    }

    public User toUser() {
        User user = new User();
        user.setActive(isActive);
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

    public void updateUser(User user) {
        user.setActive(isActive);
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
    }
}
