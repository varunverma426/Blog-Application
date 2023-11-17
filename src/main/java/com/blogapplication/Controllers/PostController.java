package com.blogapplication.Controllers;

import com.blogapplication.Services.Service.FileService;
import com.blogapplication.Services.Service.PostService;
import com.blogapplication.Utils.ApiResponse;
import com.blogapplication.config.AppConstant;
import com.blogapplication.payloads.PaginitationResponse;
import com.blogapplication.payloads.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

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
    public ResponseEntity<PaginitationResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) int pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) int pageSize,
                                                           @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
                                                           @RequestParam(value = "sortDir", defaultValue = AppConstant.PAGE_DIR, required = false) String sortDir) {
        PaginitationResponse getAllPost = this.postService.getAllPost(pageNumber, pageSize,sortBy,sortDir);
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

//    @GetMapping("/search/{keyword}")
//    public ResponseEntity<List<PostDTO>> searchPost(@PathVariable String keyword){
//        List<PostDTO> postDTOList=this.postService.searchPost(keyword);
//        return new ResponseEntity<List<PostDTO>>(postDTOList,HttpStatus.OK);
//    }

    //Uploading Image for the particular post
    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadImage(@RequestParam MultipartFile image, @PathVariable int postId) throws IOException {

        //retrieve the post in which image needs to be uploaded
        PostDTO postDTO=this.postService.getPostById(postId);

        //upload image
        String fileName=this.fileService.uploadImage(path,image);

        //After uploading image update the image name in the db
        postDTO.setImageName(fileName);
        return new ResponseEntity<PostDTO>(this.postService.updatePost(postDTO,postId),HttpStatus.OK);
    }

    //Serving or downloading Image
    @GetMapping(value= "/image/download/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream resource=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
