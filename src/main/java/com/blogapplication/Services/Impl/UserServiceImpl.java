package com.blogapplication.Services.Impl;

import com.blogapplication.Entities.UserEntity;
import com.blogapplication.Exceptions.ResourceNotFound;
import com.blogapplication.Repositories.UserDAO;
import com.blogapplication.Services.UserService;
import com.blogapplication.payloads.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity users=this.DTOtoEntity(userDTO);
        UserEntity savedUser=this.userDAO.save(users);
        return this.EntitytoDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, int user_id) {
        UserEntity userEntity=userDAO.findById(user_id).orElseThrow(()-> new ResourceNotFound("User","id", user_id));
        userEntity.setUser_name(userDTO.getUser_name());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setAbout(userDTO.getAbout());
        UserEntity updatedUser=this.userDAO.save(userEntity);
        return this.EntitytoDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(int user_id) {
       UserEntity userEntity=userDAO.findById(user_id).orElseThrow(()-> new ResourceNotFound("User","id", user_id));
       return this.EntitytoDTO(userEntity);

    }

    @Override
    public List<UserDTO> getAllUser() {
       List<UserEntity> userEntity= this.userDAO.findAll();
      List<UserDTO>userDTOList=userEntity.stream().map(user->this.EntitytoDTO(user)).collect(Collectors.toList());
      return userDTOList;
    }

    @Override
    public void deleteUser(int user_id) {
        UserEntity userEntity=userDAO.findById(user_id).orElseThrow(()-> new ResourceNotFound("User","id", user_id));
        this.userDAO.delete(userEntity);

    }
    private UserEntity DTOtoEntity(UserDTO userDTO){
        //Use model mapper instead of doing manually
        UserEntity userEntity=modelMapper.map(userDTO,UserEntity.class);
//      UserEntity user=new UserEntity();
//      user.setUser_id(userDTO.getUser_id());
//      user.setUser_name(userDTO.getUser_name());
//      user.setEmail(userDTO.getEmail());
//      user.setPassword(userDTO.getPassword());
//      user.setAbout(userDTO.getAbout());
      return userEntity;
    }
    private UserDTO EntitytoDTO(UserEntity users){
        //Use model mapper instead of doing manually
        UserDTO userDTO=modelMapper.map(users,UserDTO.class);
//        UserDTO userDTO=new UserDTO();
//        userDTO.setUser_id(users.getUser_id());
//        userDTO.setUser_name(users.getUser_name());
//        userDTO.setEmail(users.getEmail());
//        userDTO.setPassword(users.getPassword());
//        userDTO.setAbout(users.getAbout());
        return userDTO;
    }
}
