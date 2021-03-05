package com.example.blog.web.admin;

import com.example.blog.po.Tag;
import com.example.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(){
        return "admin/tags-input";
    }

//    添加
    @PostMapping("/tags")
    public String post(Tag tag){
        Tag t = tagService.saveTag(tag);
    return "redirect:/admin/tags";
}
//修改
@GetMapping("/tags/{id}/input")
public String editInput(@PathVariable Long id, Model model){
    model.addAttribute("tag",tagService.getTag(id));
    return "admin/tags-input";
}
    @PostMapping("/tags/{id}")
    public String post(Tag tag, @PathVariable Long id){
        Tag t = tagService.updateTag(id,tag);
        return "redirect:/admin/tags";
    }


    //删除
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id){
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }
}
