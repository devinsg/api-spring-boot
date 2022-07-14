package com.demo.airline.repositories;

import com.demo.airline.models.PricingCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPricingCategoryRepository extends JpaRepository<PricingCategory, Long> {
}
