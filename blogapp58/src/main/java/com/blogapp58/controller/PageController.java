package com.blogapp58.controller;

import com.blogapp58.payload.ListPageDto;
import com.blogapp58.payload.PageDto;
import com.blogapp58.service.PageService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/page")
public class PageController {

    private PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    // http:localhost:8080/api/page
    @PostMapping
    public ResponseEntity<?> createPage(@Valid @RequestBody PageDto pageDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PageDto dto = pageService.createPage(pageDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/page/2
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePage(@PathVariable long id){
        pageService.deletePage(id);
        return new ResponseEntity<>("Page deleted!", HttpStatus.OK);
    }

    //http://localhost:8080/api/page?pageNo=0&pageSize=3&sortBy=title&sortDir=desc
    @GetMapping
    public ResponseEntity<ListPageDto> fetchAll(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "5",required = false)int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false)String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "asc",required = false)String sortDir
    ){
        ListPageDto listPageDto = pageService.fetchAll(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(listPageDto, HttpStatus.OK);
    }

    // http://localhost:8080/api/page/1
    @GetMapping("/{id}")
    public ResponseEntity<PageDto> getPageById(@PathVariable long id){
        PageDto dto = pageService.getPageById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
