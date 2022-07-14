package com.demo.airline.repositories;

import com.demo.airline.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "staff", path = "employees")
public interface ITrainingRepository extends JpaRepository<Training, Long> {
}
