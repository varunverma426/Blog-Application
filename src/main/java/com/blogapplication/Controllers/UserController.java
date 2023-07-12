package com.blogapplication.Controllers;

import com.blogapplication.Services.UserService;
import com.blogapplication.payloads.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO createdUser=this.userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
//    @PutMapping(@RequestBody UserDTO userDTO)
//    public UserDTO addUser(@RequestBody UserDTO userDTO){
//        return this.userService.createUser(userDTO);
//    }
//    @GetMapping("/getAllUser")
//    public Response

}
