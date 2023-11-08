package com.blogapplication.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

    private String postId;
    private String postTitle;
    private String content;

    private String imageName = "default.png";
    private Date postDate;

    private CategoryDTO category;

    private UserDTO  user;
}