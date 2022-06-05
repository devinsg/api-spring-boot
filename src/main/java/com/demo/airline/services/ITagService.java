package com.demo.airline.services;

import com.demo.airline.models.Tag;

import java.util.List;

public interface ITagService {
    List<Tag> queryByTagName(String name);

    Tag queryByTagId(Integer id);
}
