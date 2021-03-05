package com.example.blog.service;

import com.example.blog.dao.TagRepository;
import com.example.blog.po.Tag;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService{


    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) { //1,2,3
        return tagRepository.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids)&& ids!=null){
            String[] idarray = ids.split(",");
            for (int i=0;i<idarray.length;i++){
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.getOne(id);
        if(t == null){
            try {
                throw new NotFoundException("没有该标签");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        PageRequest pageable = PageRequest.of(0,size,sort);
        return tagRepository.findTop(pageable);

    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
