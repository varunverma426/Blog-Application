package com.blogapplication.Controllers;

import com.blogapplication.Services.Service.UserService;
import com.blogapplication.Utils.ApiResponse;
import com.blogapplication.payloads.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable int userId) {
        UserDTO createdUser = this.userService.updateUser(userDTO, userId);
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity(new ApiResponse("User deleted sucessfully", true), HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDTO>> getUser() {
        return (ResponseEntity.ok(this.userService.getAllUser()));
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {
        return (ResponseEntity.ok(this.userService.getUserById(userId)));
    }
//    @PutMapping(@RequestBody UserDTO userDTO)
//    public UserDTO addUser(@RequestBody UserDTO userDTO){
//        return this.userService.createUser(userDTO);
//    }
//    @GetMapping("/getAllUser")
//    public Response

}
