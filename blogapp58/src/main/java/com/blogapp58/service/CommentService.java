package com.blogapp58.service;

import com.blogapp58.payload.CommentDto;
import com.blogapp58.payload.PageWithCommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, long pageId);

    PageWithCommentDto getAllCommentsByPageId(long id);

    CommentDto getCommentById(long id);
}
