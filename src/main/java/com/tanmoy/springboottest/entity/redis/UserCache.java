package com.tanmoy.springboottest.entity.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCache implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    private Long userId;
    private String username;
    private String password;
    private boolean isActive;
    private String firstName;
    private String lastName;

}
