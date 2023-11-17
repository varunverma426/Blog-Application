package com.blogapplication.Controllers;

import com.blogapplication.Services.Service.CommentService;
import com.blogapplication.Utils.ApiResponse;
import com.blogapplication.payloads.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/createComment/{postId}/{userId}")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postId,@PathVariable Integer userId) {
       CommentDTO createComment= this.commentService.createComment(commentDTO,postId,userId);
        return new ResponseEntity<CommentDTO>(createComment, HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
      this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Succesfully",true), HttpStatus.OK);
    }
}

