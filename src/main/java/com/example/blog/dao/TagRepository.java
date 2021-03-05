package com.example.blog.dao;

import com.example.blog.po.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {

    @Query("select t from Tag t")
    List<Tag> findTop(PageRequest pageable);
}
