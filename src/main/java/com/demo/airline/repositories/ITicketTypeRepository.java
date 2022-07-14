package com.demo.airline.repositories;

import com.demo.airline.models.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITicketTypeRepository extends JpaRepository<TicketType, Long> {
    List<TicketType> findByIncludesWorkshopTrue();
}
