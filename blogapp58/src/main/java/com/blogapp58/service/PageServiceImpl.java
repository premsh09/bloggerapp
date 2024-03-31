package com.blogapp58.service;

import com.blogapp58.entity.Page;
import com.blogapp58.exception.ResourceNotFound;
import com.blogapp58.payload.ListPageDto;
import com.blogapp58.payload.PageDto;
import com.blogapp58.repository.PageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PageServiceImpl implements PageService{


    private PageRepository pageRepository;

    private ModelMapper modelMapper;

    public PageServiceImpl(PageRepository pageRepository, ModelMapper modelMapper) {
        this.pageRepository = pageRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public PageDto createPage(PageDto pageDto) {
        Page page = mapToEntity(pageDto);

        Page savedPage = pageRepository.save(page);

        PageDto dto = mapToDto(savedPage);

        return dto;
    }

    @Override
    public void deletePage(long id) {
        pageRepository.deleteById(id);
    }

    @Override
    public ListPageDto fetchAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        org.springframework.data.domain.Page<Page> all = pageRepository.findAll(pageable);
        List<Page> pages = all.getContent();
        List<PageDto> pageDtos = pages.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        ListPageDto listPageDto = new ListPageDto();
        listPageDto.setPageDto(pageDtos);
        listPageDto.setTotalPage(all.getTotalPages());
        listPageDto.setTotalElement((int) all.getTotalElements());
        listPageDto.setFirstPage(all.isFirst());
        listPageDto.setLastPage(all.isLast());
        listPageDto.setPageNumber(all.getNumber());
        return listPageDto;
    }

    @Override
    public PageDto getPageById(long id) {
        Page page = pageRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Page not found with id :" + id));
        return mapToDto(page);
    }

    Page mapToEntity(PageDto pageDto){
       Page page = modelMapper.map(pageDto, Page.class);
       return page;
   }

   PageDto mapToDto(Page page){
       PageDto dto = modelMapper.map(page, PageDto.class);
       return dto;
   }
}
