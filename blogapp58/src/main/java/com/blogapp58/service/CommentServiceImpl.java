package com.blogapp58.service;

import com.blogapp58.entity.Comment;
import com.blogapp58.entity.Page;
import com.blogapp58.exception.ResourceNotFound;
import com.blogapp58.payload.CommentDto;
import com.blogapp58.payload.PageDto;
import com.blogapp58.payload.PageWithCommentDto;
import com.blogapp58.repository.CommentRepository;
import com.blogapp58.repository.PageRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private PageRepository pageRepository;
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, long pageId) {
        Optional<Page> byId = pageRepository.findById(pageId);
        Page page = byId.get();

        Comment comment = mapToEntity(commentDto);
        comment.setPage(page);
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    @Override
    public PageWithCommentDto getAllCommentsByPageId(long id) {
        Page page = pageRepository.findById(id).get();
        PageDto dto = new PageDto();
        dto.setId(page.getId());
        dto.setTitle(page.getTitle());
        dto.setContent(page.getContent());
        dto.setDescription(page.getDescription());

        List<Comment> comments = commentRepository.findByPageId(id);
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());

        PageWithCommentDto pageWithCommentDto = new PageWithCommentDto();
        pageWithCommentDto.setPage(dto);
        pageWithCommentDto.setCommentDto(dtos);
        return pageWithCommentDto;
    }

    @Override
    public CommentDto getCommentById(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Comment not found with id :" + id));
        return mapToDto(comment);
    }

    Comment mapToEntity(CommentDto dto){
        return modelMapper.map(dto, Comment.class);
    }

    CommentDto mapToDto(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }
}
