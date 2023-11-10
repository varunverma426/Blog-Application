package com.blogapplication.Controllers;

import com.blogapplication.Services.Service.PostService;
import com.blogapplication.Utils.ApiResponse;
import com.blogapplication.payloads.PaginitationResponse;
import com.blogapplication.payloads.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postInput, @PathVariable int userId, @PathVariable int categoryId) {
        PostDTO createdPost = this.postService.createPost(postInput, userId, categoryId);
        return new ResponseEntity<PostDTO>(createdPost, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable int userId) {
        List<PostDTO> postByUser = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDTO>>(postByUser, HttpStatus.OK);
    }

    @GetMapping("category/{categoryId}")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable int categoryId) {
        List<PostDTO> postBycategory = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDTO>>(postBycategory, HttpStatus.OK);
    }

    @GetMapping("/getAllPost")
    public ResponseEntity<PaginitationResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        PaginitationResponse getAllPost = this.postService.getAllPost(pageNumber,pageSize);
        return new ResponseEntity<PaginitationResponse>(getAllPost, HttpStatus.OK);
    }

    @GetMapping("/getPostById/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int postId) {
        PostDTO post = postService.getPostById(postId);
        return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
    }

    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity(new ApiResponse("Post deleted sucessfully", true), HttpStatus.OK);
    }

    @PutMapping("/updatePost/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable int postId) {
        PostDTO updatedPost = this.postService.updatePost(postDTO, postId);
        return new ResponseEntity<PostDTO>(updatedPost, HttpStatus.OK);
    }
}
