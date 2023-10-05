package com.example.trustbankcrud.service;

import com.example.trustbankcrud.dto.BaseResponse;
import com.example.trustbankcrud.dto.CreateUserRequest;
import com.example.trustbankcrud.dto.UpdateUserRequest;
import com.example.trustbankcrud.exceptions.InvalidRequestException;
import org.springframework.http.ResponseEntity;

public interface UserService {
    BaseResponse<?> createUser(CreateUserRequest createUserRequest) throws InvalidRequestException;
    BaseResponse<?> updateUser(Long userId, UpdateUserRequest updateUserRequest) throws InvalidRequestException;

    BaseResponse<?> deleteUser(Long userId);

    BaseResponse<?> getUser(Long userId);

    BaseResponse<?> getAllUsers();
}
