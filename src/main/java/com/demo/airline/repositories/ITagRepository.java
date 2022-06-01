package com.demo.airline.repositories;

import com.demo.airline.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByTagNameContains(String name);
}
