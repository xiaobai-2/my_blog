package com.example.blog.service;

import com.example.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TagService {

    //保存tag类
    Tag saveTag(Tag tag);
    //根据id查询
    Tag getTag(Long id);
    //分页
    Page<Tag> listTag(Pageable pageable);
    //根据id修改
    List<Tag> listTag();

    List<Tag> listTagTop(Integer size);

    List<Tag> listTag(String ids);
    Tag updateTag(Long id,Tag tag);
    //删除
    void deleteTag(Long id);
}
