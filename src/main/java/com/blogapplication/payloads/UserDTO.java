package com.blogapplication.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO{

        private int user_id;


        private String user_name;
        private String email;
        private String password;


        private String about;
}
