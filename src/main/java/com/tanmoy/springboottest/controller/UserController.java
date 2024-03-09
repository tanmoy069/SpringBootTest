package com.tanmoy.springboottest.controller;

import com.tanmoy.springboottest.dto.UserDto;
import com.tanmoy.springboottest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/save")
    public ResponseEntity<Object> save(@RequestBody UserDto userDto) {
        return ok(userService.saveUser(userDto));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody UserDto userDto) {
        if (Objects.isNull(userDto.getUserId())) {
            return ok("Please provide user id to update user");
        }
        return ok(userService.updateUser(userDto));
    }

    @GetMapping(value = "/find-by-id/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return ok(userService.findById(id));
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<Object> findAll() {
        return ok(userService.findAll());
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        if (Objects.isNull(id)) {
            return ok("Please provide user id to delete user");
        }
        boolean isDeleted = userService.deleteUser(id);
        return ok(isDeleted ? "User has been deleted successfully" : "Failed to delete user");
    }
}
