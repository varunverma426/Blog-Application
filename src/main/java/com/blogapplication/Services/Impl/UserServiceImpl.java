package com.blogapplication.Services.Impl;

import com.blogapplication.Entities.UserEntity;
import com.blogapplication.Exceptions.ResourceNotFound;
import com.blogapplication.Repositories.UserDAO;
import com.blogapplication.Services.Service.UserService;
import com.blogapplication.payloads.PaginitationResponse;
import com.blogapplication.payloads.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PaginitationResponse getAllUser(int pageNumber,int pageSize,String sortBy,String sortDir) {

        //Create Sorting object and doing sorting according to sort direction
        Sort sorting = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable page= PageRequest.of(pageNumber,pageSize,sorting);
        Page<UserEntity> usersPage=this.userDAO.findAll(page);
        List<UserEntity> userEntities= usersPage.getContent();
        List<UserDTO> userDTOS=userEntities.stream().map(p->this.modelMapper.map(p,UserDTO.class)).collect(Collectors.toList());

        PaginitationResponse paginitationResponse=new PaginitationResponse();
        paginitationResponse.setContentUser(userDTOS);
        paginitationResponse.setPageNumber(usersPage.getNumber());
        paginitationResponse.setPageSize(usersPage.getSize());
        paginitationResponse.setTotalElement(usersPage.getTotalElements());
        paginitationResponse.setTotalPages(usersPage.getTotalPages());
        paginitationResponse.setLastPage(usersPage.isLast());
       return paginitationResponse;
    }

    @Override
    public void deleteUser(int user_id) {
        UserEntity userEntity=userDAO.findById(user_id).orElseThrow(()-> new ResourceNotFound("User","id", user_id));
        this.userDAO.delete(userEntity);

    }
    private UserEntity DTOtoEntity(UserDTO userDTO){
        //Use model mapper instead of doing manually
        UserEntity userEntity=modelMapper.map(userDTO,UserEntity.class);
      return userEntity;
    }
    private UserDTO EntitytoDTO(UserEntity users){
        //Use model mapper instead of doing manually
        UserDTO userDTO=modelMapper.map(users,UserDTO.class);

        return userDTO;
    }
}
