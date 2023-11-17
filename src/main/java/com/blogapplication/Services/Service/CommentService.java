package com.blogapplication.Services.Service;

import com.blogapplication.payloads.CommentDTO;

public interface CommentService {

    CommentDTO createComment(CommentDTO comment, Integer postId, Integer userId);

    void deleteComment(Integer commentId);
}
