package com.demo.airline.services;

import com.demo.airline.models.Tag;

import java.util.List;

public interface ITagService {
    List<Tag> queryByTagNameContains(String tagName);
    List<Tag> queryAll();
    List<Tag> queryAllAsc();
    List<Tag> queryAllDesc();
    Tag queryByTagId(long tagId);
    Tag update(Tag tag);
    Tag create(Tag tag);
}
