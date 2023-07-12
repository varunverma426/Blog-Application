package com.blogapplication.Services.Impl;

import com.blogapplication.Entities.UserEntity;
import com.blogapplication.Repositories.UserDAO;
import com.blogapplication.Services.UserService;
import com.blogapplication.payloads.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity users=this.DTOtoEntity(userDTO);
        UserEntity savedUser=this.userDAO.save(users);
        return this.EntitytoDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, int user_id) {
//        UserEntity users=this.DTOtoEntity(userDTO);
//        UserEntity savedUser=this.userDAO.
//        return this.EntitytoDTO(savedUser);
        return null;
    }

    @Override
    public UserDTO getUserById(int user_id) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUser() {
        return null;
    }

    @Override
    public void deleteUser(int user_id) {

    }
    private UserEntity DTOtoEntity(UserDTO userDTO){
      UserEntity user=new UserEntity();
      user.setUser_id(userDTO.getUser_id());
      user.setUser_name(userDTO.getUser_name());
      user.setEmail(userDTO.getEmail());
      user.setPassword(userDTO.getPassword());
      user.setAbout(userDTO.getAbout());
      return user;
    }
    private UserDTO EntitytoDTO(UserEntity users){
        UserDTO userDTO=new UserDTO();
        userDTO.setUser_id(users.getUser_id());
        userDTO.setUser_name(users.getUser_name());
        userDTO.setEmail(users.getEmail());
        userDTO.setPassword(users.getPassword());
        userDTO.setAbout(users.getAbout());
        return userDTO;
    }
}
