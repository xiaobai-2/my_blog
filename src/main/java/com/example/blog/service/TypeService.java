package com.example.blog.service;

import com.example.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    //保存Type类
    Type saveType(Type type);
    //根据id查询Type
    Type getType(Long id);
    //分页查询
    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    //修改后的type
    Type updateType(Long id,Type type);
    //删除分类
    void deleteType(Long id);
}
