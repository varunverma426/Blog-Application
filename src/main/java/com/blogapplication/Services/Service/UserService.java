package com.blogapplication.Services.Service;

import com.blogapplication.payloads.PaginitationResponse;
import com.blogapplication.payloads.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO, int user_id);
    UserDTO getUserById(int user_id);
    PaginitationResponse getAllUser(int pageNumber, int pageSize,String sortBy,String sortDir);
    void deleteUser(int user_id);
}
