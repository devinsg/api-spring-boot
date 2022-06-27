package com.demo.airline.services;

import com.demo.airline.models.Tag;
import com.demo.airline.repositories.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TagService implements ITagService {
    @Autowired
    private ITagRepository tagRepository;

    public List<Tag> queryByTagName(String tagName) {
        return tagRepository.findByTagNameContains(tagName);
    }

    public Tag queryByTagId(Integer tagId) {
        return tagRepository.getById((long)tagId);
    }

    public TagService() {

    }
}
