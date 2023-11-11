package com.blogapplication.Controllers;

import com.blogapplication.Services.Service.UserService;
import com.blogapplication.Utils.ApiResponse;
import com.blogapplication.config.AppConstant;
import com.blogapplication.payloads.PaginitationResponse;
import com.blogapplication.payloads.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<PaginitationResponse> getUser(@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) int pageNumber,
                                                        @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) int pageSize,
                                                        @RequestParam(value = "sortBy", defaultValue = "user_name", required = false) String sortBy,
                                                        @RequestParam(value = "sortDir", defaultValue = AppConstant.PAGE_DIR, required = false) String sortDir) {
        return (ResponseEntity.ok(this.userService.getAllUser(pageNumber,pageSize,sortBy,sortDir)));
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
