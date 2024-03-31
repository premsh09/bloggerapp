package com.blogapp58.controller;

import com.blogapp58.payload.CommentDto;
import com.blogapp58.payload.PageWithCommentDto;
import com.blogapp58.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    // http://localhost:8080/api/comments/1
    @PostMapping("/{pageId}")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable long pageId){

        CommentDto dto = commentService.createComment(commentDto, pageId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/comments/1
    @GetMapping("/{pageId}")
    public ResponseEntity<PageWithCommentDto> getAllCommentsByPageId(@PathVariable long pageId){
        PageWithCommentDto allCommentsByPageId = commentService.getAllCommentsByPageId(pageId);
        return new ResponseEntity<>(allCommentsByPageId, HttpStatus.OK);
    }

    // http://localhost:8080/api/comments?id=1
    @GetMapping
    public ResponseEntity<CommentDto> getCommentById(@RequestParam long id){
        CommentDto dto = commentService.getCommentById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
