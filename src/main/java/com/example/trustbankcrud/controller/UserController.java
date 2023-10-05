package com.example.trustbankcrud.controller;

import com.example.trustbankcrud.dto.BaseResponse;
import com.example.trustbankcrud.dto.CreateUserRequest;
import com.example.trustbankcrud.dto.UpdateUserRequest;
import com.example.trustbankcrud.service.Impl.UserServiceImpl;
import com.example.trustbankcrud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class UserController {

    private final UserService userService;


    @PostMapping("users")
    public ResponseEntity<BaseResponse<?>> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return new ResponseEntity<>(userService.createUser(createUserRequest), HttpStatus.CREATED);
    }
    @PutMapping("users/{userId}")
    public ResponseEntity<BaseResponse<?>> updateUser(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable("userId") Long userId) {
        return new ResponseEntity<>(userService.updateUser(userId, updateUserRequest), HttpStatus.OK);
    }
    @DeleteMapping("users/{userId}")
    public ResponseEntity<BaseResponse<?>> deleteUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<BaseResponse<?>> getUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<BaseResponse<?>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

}
