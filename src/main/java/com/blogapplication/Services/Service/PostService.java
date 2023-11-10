package com.blogapplication.Services.Service;

import com.blogapplication.payloads.PaginitationResponse;
import com.blogapplication.payloads.PostDTO;

import java.util.List;


public interface PostService {
    //create
    PostDTO createPost(PostDTO postDTO,int userId,int categoryId);

    //update
    PostDTO updatePost(PostDTO postDTO, int postId);

    PostDTO getPostById(int postId);

    PaginitationResponse getAllPost(int pageNumber, int pageSize);

    void deletePost(int postId);

    //getAllPostBycategory
    List<PostDTO> getPostByCategory(int categoryId);

    List<PostDTO> getPostByUser(int userId);

    //search Post by keyword
    List<PostDTO> searchPost(String keyword);
}
