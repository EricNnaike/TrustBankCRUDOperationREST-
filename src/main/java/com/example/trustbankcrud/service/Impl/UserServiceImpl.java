package com.example.trustbankcrud.service.Impl;

import com.example.trustbankcrud.dto.AddressRequest;
import com.example.trustbankcrud.dto.BaseResponse;
import com.example.trustbankcrud.dto.CreateUserRequest;
import com.example.trustbankcrud.dto.UpdateUserRequest;
import com.example.trustbankcrud.entity.Address;
import com.example.trustbankcrud.entity.User;
import com.example.trustbankcrud.exceptions.InvalidRequestException;
import com.example.trustbankcrud.exceptions.ResourceNotFoundException;
import com.example.trustbankcrud.repository.UserRepository;
import com.example.trustbankcrud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public BaseResponse<?> createUser(CreateUserRequest createUserRequest) throws InvalidRequestException {

        Optional<User> existingUser = userRepository.findByEmail(createUserRequest.getEmail());

        if (existingUser.isPresent()) {
            throw new InvalidRequestException("User with email " + createUserRequest.getEmail() + " already exists.");
        }

        User newUser = createUserFromRequest(createUserRequest);
        userRepository.save(newUser);

        return BaseResponse.builder()
                .message("User created successfully.")
                .data(newUser)
                .build();
    }

    @Override
    public BaseResponse<?> updateUser(Long userId, UpdateUserRequest updateUserRequest) throws InvalidRequestException {
        // Check if the user with the given ID exists
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            return BaseResponse.builder()
                    .message("User not found.")
                    .build();
        }

        User updatedUser = userRepository.save(updateUserInformation(userOptional.get(), updateUserRequest));
        return BaseResponse.builder()
                .message("User updated successfully.")
                .data(updatedUser)
                .build();
    }

    @Override
    public BaseResponse<?> deleteUser(Long userId) {
        // Check if the user with the given ID exists
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found.");
        }

        // Delete the user from the database
        userRepository.deleteById(userId);

        return BaseResponse.builder()
                .message("User deleted successfully.")
                .build();
    }

    @Override
    public BaseResponse<?> getUser(Long userId) {
        // Check if the user with the given ID exists
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
               throw new ResourceNotFoundException("User not found.");
        }

        // Retrieve the user
        User user = userOptional.get();

        return BaseResponse.builder()
                .message("User retrieved successfully.")
                .data(user)
                .build();
    }

    @Override
    public BaseResponse<?> getAllUsers() {
        List<User> users = userRepository.findAll();

        return BaseResponse.builder()
                .message("All users retrieved successfully.")
                .data(users)
                .build();
    }


    private User createUserFromRequest(CreateUserRequest createUserRequest) {
        Address address = Address.builder()
                .street(createUserRequest.getAddress().getStreet())
                .city(createUserRequest.getAddress().getCity())
                .state(createUserRequest.getAddress().getState())
                .zipCode(createUserRequest.getAddress().getZipCode())
                .build();

        return User.builder()
                .name(createUserRequest.getName())
                .email(createUserRequest.getEmail())
                .phone(createUserRequest.getPhone())
                .address(address)
                .build();
    }

    private User updateUserInformation(User user, UpdateUserRequest updateUserRequest) {
        user.setName(updateUserRequest.getName());
        user.setEmail(updateUserRequest.getEmail());
        user.setPhone(updateUserRequest.getPhone());

        if (user.getAddress() != null && updateUserRequest.getAddress() != null) {
            Address userAddress = user.getAddress();
            AddressRequest updateAddress = updateUserRequest.getAddress();

            userAddress.setStreet(updateAddress.getStreet());
            userAddress.setCity(updateAddress.getCity());
            userAddress.setState(updateAddress.getState());
            userAddress.setZipCode(updateAddress.getZipCode());
        }
        return user;
    }
}
