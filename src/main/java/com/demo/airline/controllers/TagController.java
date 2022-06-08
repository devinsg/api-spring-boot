package com.demo.airline.controllers;

import com.demo.airline.models.Tag;
import com.demo.airline.services.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag")
public class TagController {
    @Autowired
    private ITagService tagService;

    @GetMapping
    public List<Tag> list(@RequestParam(required = false) String name) {
        if (name != null) {
            return tagService.queryByTagNameContains(name);
        } else {
            //return tagService.queryAll();
            //return tagService.queryAllAsc();
            return tagService.queryAllDesc();
        }
    }

    @GetMapping
    @RequestMapping("{id}")
    public Tag get(@PathVariable Integer id) {
        return tagService.queryByTagId((long)id);
    }

    @PostMapping
    public Tag create(@RequestBody final Tag tag){
        return tagService.create(tag);
    }

    @PutMapping
    public Tag update(@RequestBody final Tag tag){
        return tagService.update(tag);
    }
}
