package com.blogapp58.payload;

import lombok.Data;

import java.util.List;

@Data
public class PageWithCommentDto {

    private PageDto page;
    private List<CommentDto> commentDto;
}
