package com.blogapplication.Services;

import com.blogapplication.payloads.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO, int user_id);
    UserDTO getUserById(int user_id);
    List<UserDTO> getAllUser();
    void deleteUser(int user_id);
}
