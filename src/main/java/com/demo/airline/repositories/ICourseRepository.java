package com.demo.airline.repositories;

import com.demo.airline.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "course", path = "courses")
public interface ICourseRepository extends JpaRepository<Course, Long> {
}
