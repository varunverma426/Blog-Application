package com.blogapplication.Services.Impl;

import com.blogapplication.Entities.CommentEntity;
import com.blogapplication.Entities.PostEntity;
import com.blogapplication.Entities.UserEntity;
import com.blogapplication.Exceptions.ResourceNotFound;
import com.blogapplication.Repositories.CommentDAO;
import com.blogapplication.Repositories.PostDao;
import com.blogapplication.Repositories.UserDAO;
import com.blogapplication.Services.Service.CommentService;
import com.blogapplication.payloads.CommentDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostDao post;

    @Autowired
    private UserDAO user;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDTO createComment(CommentDTO comment, Integer postId, Integer userId) {
        PostEntity postEntity=this.post.findById(postId)
                .orElseThrow(()->new ResourceNotFound("Post","PostId",postId));

        UserEntity userEntity=this.user.findById(userId)
                .orElseThrow(()->new ResourceNotFound("User","UserId",userId));

        CommentEntity commentEntity=this.modelMapper.map(comment,CommentEntity.class);
        commentEntity.setPost(postEntity);
        commentEntity.setUser(userEntity);
        commentEntity.setPostId(postEntity.getPostId());

        CommentDTO savedComment= this.modelMapper.map(this.commentDAO.save(commentEntity),CommentDTO.class);
        return savedComment;
    }

    @Override
    public void deleteComment(Integer commentId) {
        CommentEntity commentEntity=this.commentDAO.findById(commentId)
                .orElseThrow(()->new ResourceNotFound("Comment","CommentId",commentId));

        this.commentDAO.delete(commentEntity);
    }
}
