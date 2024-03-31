package com.blogapp58.payload;

import lombok.Data;

import java.util.List;
@Data
public class ListPageDto {
    private List<PageDto> pageDto;
    private int totalPage;
    private int totalElement;
    private int pageNumber;
    private boolean firstPage;
    private boolean lastPage;

}
