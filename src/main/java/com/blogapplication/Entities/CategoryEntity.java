package com.blogapplication.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "categoryTitle", length = 100)
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 5, message = "Min size of categoryTitle is 4")
    private String categoryTitle;

    @Column(name = "categoryDescription", length = 100)
    @Size(min = 10, message = "Min size of category description is 10")
    private String categoryDescription;


    //cascade is use so that child record gets deleted with parent record
    //establishing one to many relationship
    //one to many relationship as one category can have many posts
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<PostEntity> posts = new HashSet<>();
}
