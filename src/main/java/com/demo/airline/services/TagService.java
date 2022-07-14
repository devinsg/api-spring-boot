package com.demo.airline.services;

import com.demo.airline.models.Tag;
import com.demo.airline.repositories.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("tagService")
public class TagService implements ITagService {
    @Autowired
    private ITagRepository tagRepository;

    public List<Tag> queryByTagNameContains(String tagName) {
        return tagRepository.findByTagNameContains(tagName);
    }

    public List<Tag> queryAll() {
        return tagRepository.findAll();
    }

    public List<Tag> queryAllAsc() {
        return tagRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Tag::getTagId))
                .collect(Collectors.toList());
    }

    public List<Tag> queryAllDesc() {
        return tagRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Tag::getTagId).reversed())
                .collect(Collectors.toList());
    }

    public Tag queryByTagId(long tagId) {
        return tagRepository.getById(tagId);
    }

    @Transactional
    public Tag update(Tag tag) {
        return tagRepository.saveAndFlush(tag);
    }

    @Transactional
    public Tag create(Tag tag) {
        return tagRepository.saveAndFlush(tag);
    }
}
