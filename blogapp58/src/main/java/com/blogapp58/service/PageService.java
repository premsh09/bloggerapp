package com.blogapp58.service;

import com.blogapp58.payload.ListPageDto;
import com.blogapp58.payload.PageDto;

public interface PageService {
    PageDto createPage(PageDto pageDto);

    void deletePage(long id);

    ListPageDto fetchAll(int pageNo, int pageSize, String sortBy, String sortDir);

    PageDto getPageById(long id);
}
