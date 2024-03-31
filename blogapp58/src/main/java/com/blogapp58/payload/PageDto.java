package com.blogapp58.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class PageDto {

    private long id;
    @NotEmpty
    @Size(min = 3, message = "Title should be atleat 3 characters")
    private String title;
    private String description;
    private String content;
}
