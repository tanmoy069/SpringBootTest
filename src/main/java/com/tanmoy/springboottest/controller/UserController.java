package com.tanmoy.springboottest.controller;

import com.tanmoy.springboottest.dto.UserDto;
import com.tanmoy.springboottest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody UserDto userDto) {
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
        boolean isDeleted = userService.deleteUser(id);
        return ok(isDeleted ? "User has been deleted successfully" : "Failed to delete user");
    }
}
