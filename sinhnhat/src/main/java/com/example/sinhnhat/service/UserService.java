package com.example.sinhnhat.service;

import com.example.sinhnhat.entity.User;
import com.example.sinhnhat.model.request.ChangePasswordRequest;
import com.example.sinhnhat.model.request.CreateUserRequest;
import com.example.sinhnhat.model.request.UpdateProfileRequest;

public interface UserService {
    
    void register(CreateUserRequest request);


    User getUserByUsername(String username);

    User updateUser(UpdateProfileRequest request);

    void changePassword(ChangePasswordRequest request);

}
