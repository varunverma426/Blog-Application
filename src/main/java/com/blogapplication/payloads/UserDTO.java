package com.blogapplication.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO{

        private int user_id;

        @NotEmpty
        @NotNull
        @Size(min=4,message = "Username must be greater that 4 character")
        private String user_name;
        @Email(message = "Email address is not valid")
        @NotEmpty
        @NotNull
        private String email;
        @NotEmpty(message = "Must not be empty")
        @Size(min=4,message = "Password must be min of 3 chars")
        @NotNull
        private String password;
        @NotEmpty
        @NotNull
        private String about;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Set<CommentDTO> comment=new HashSet<>();
}
