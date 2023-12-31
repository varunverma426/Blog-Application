package com.blogapplication.Services.Impl;

import com.blogapplication.Entities.CategoryEntity;
import com.blogapplication.Entities.PostEntity;
import com.blogapplication.Entities.UserEntity;
import com.blogapplication.Exceptions.ResourceNotFound;
import com.blogapplication.Repositories.CategoryDAO;
import com.blogapplication.Repositories.PostDao;
import com.blogapplication.Repositories.UserDAO;
import com.blogapplication.Services.Service.PostService;
import com.blogapplication.payloads.PaginitationResponse;
import com.blogapplication.payloads.PostDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private UserDAO userDAO;


    @Override
    public PostDTO createPost(PostDTO postDTO, int userId, int categoryId) {

        //Extract User Details
        UserEntity userDetails = userDAO.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "User Id", userId));

        //Extract Cateory details
        CategoryEntity categoryDetails = categoryDAO.findById(categoryId).orElseThrow(() -> new ResourceNotFound("Category", "CategoryId", categoryId));

        PostEntity postInput = this.modelMapper.map(postDTO, PostEntity.class);
        postInput.setImageName("default.png");
        postInput.setPostDate(new Date());
        postInput.setUser(userDetails);
        postInput.setCategory(categoryDetails);

        PostEntity newPost = this.postDao.save(postInput);
        return this.modelMapper.map(newPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, int postId) {
        PostEntity postEntity = this.postDao.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "PostId", postId));
        postEntity.setPostTitle(postDTO.getPostTitle());
        postEntity.setContent(postDTO.getContent());
        postEntity.setImageName(postDTO.getImageName());
        PostEntity updatedPost = this.postDao.save(postEntity);
        return this.modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public PostDTO getPostById(int postId) {
        PostEntity postEntity = this.postDao.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "PostId", postId));
        return this.modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PaginitationResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir) {

        //introducing Pageinitiation concept

        //Create Sorting object and doing sorting according to sort direction
        Sort sorting = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        //creating pagable object
        Pageable page = PageRequest.of(pageNumber, pageSize, sorting);
        Page<PostEntity> pagePost = this.postDao.findAll(page);
        List<PostEntity> postEntity = pagePost.getContent();
        List<PostDTO> postDTOS = postEntity.stream().map(p -> this.modelMapper.map(p, PostDTO.class)).collect(Collectors.toList());

        PaginitationResponse PaginitationResponse = new PaginitationResponse();
        PaginitationResponse.setContentPost(postDTOS);
        PaginitationResponse.setPageNumber(pagePost.getNumber());
        PaginitationResponse.setPageSize(pagePost.getSize());
        PaginitationResponse.setTotalElement(pagePost.getTotalElements());
        PaginitationResponse.setTotalPages(pagePost.getTotalPages());
        PaginitationResponse.setLastPage(pagePost.isLast());

        return PaginitationResponse;
    }

    @Override
    public void deletePost(int postId) {
        PostEntity postEntity = this.postDao.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "PostId", postId));
        this.postDao.delete(postEntity);
    }

    @Override
    public List<PostDTO> getPostByCategory(int categoryId) {
        CategoryEntity categoryEntity = this.categoryDAO.findById(categoryId).orElseThrow(() -> new ResourceNotFound("Category", "CategoryId", categoryId));
        List<PostEntity> post = this.postDao.findByCategory(categoryEntity);
        return post.stream().map(p -> this.modelMapper.map(p, PostDTO.class)).collect(Collectors.toList());

    }

    @Override
    public List<PostDTO> getPostByUser(int userId) {
        UserEntity users = this.userDAO.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "userId", userId));
        List<PostEntity> postByuser = this.postDao.findByUser(users);
        return postByuser.stream().map(p -> this.modelMapper.map(p, PostDTO.class)).collect(Collectors.toList());

    }

//    @Override
//    public List<PostDTO> searchPost(String keyword) {
//        List<PostEntity> post=this.postDao.findByTitleContaining(keyword);
//        List<PostDTO> postDTOs= post.stream().map(p->this.modelMapper.map(p,PostDTO.class)).collect(Collectors.toList());
//        return postDTOs;
//    }
}
