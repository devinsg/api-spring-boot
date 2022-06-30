package com.demo.airline.repositories;

import com.demo.airline.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "trainingClasses", path = "training")
public interface IEmployeeRepository extends
        JpaRepository<Employee, Long>,
        PagingAndSortingRepository<Employee, Long>,
        CrudRepository<Employee, Long> {
    List<Employee> findByLastNameLike(String lastName);
}
