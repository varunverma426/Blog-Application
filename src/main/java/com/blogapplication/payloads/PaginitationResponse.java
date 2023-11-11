package com.blogapplication.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PaginitationResponse {

    /*This class will be used to modify the getAll Api response for pagination and to add pagesize,totalElement etc fields
    * Adding fileds like totalElement pagesize pageNumber etc*/

    //Adding JSON include so that field is not included in the response if null
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PostDTO> contentPost;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<UserDTO> contentUser;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CategoryDTO> contentCategory;

    private int pageNumber;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean isLastPage;


}
