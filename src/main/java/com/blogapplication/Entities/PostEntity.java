package com.blogapplication.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer postId;

    @NotBlank
    @Column(length=100,nullable = false)
    @Size(min=5,message = "Min size of postTitle is 5")
    private String postTitle;

    @NotBlank
    @Column(length=10000,nullable = false)
    @Size(min=10,message = "Min size of content is 10")
    private String content;

    private String imageName;
    private Date postDate;

    //Many to one relationship as multiple post can belong to one single user or category
    @ManyToOne
    private CategoryEntity category;
    @ManyToOne
    private UserEntity user;

    
}
