package com.blogapplication.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int user_id;

    @Column(nullable = false, length = 100)
    private String user_name;
    private String email;
    private String password;

    @Column(length = 250)
    private String about;
}
