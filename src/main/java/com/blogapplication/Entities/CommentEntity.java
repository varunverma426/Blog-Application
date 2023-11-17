package com.blogapplication.Entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="comment")
public class CommentEntity  {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name="comment_content",length=100,nullable = false)
    private String content;

    private Integer postId;

    @ManyToOne
    PostEntity post;

    @ManyToOne
    UserEntity user;
}
