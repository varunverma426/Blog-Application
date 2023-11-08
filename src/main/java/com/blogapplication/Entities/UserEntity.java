package com.blogapplication.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @Column(nullable = false, length = 100)
    private String user_name;
    private String email;
    private String password;
    @Column(length = 250)
    private String about;

    //cascade is use so that child record gets deleted with parent record
    //establishing one to many relationship
    //one to many relationship as one user can have many posts
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<PostEntity> posts = new HashSet<>();
}
