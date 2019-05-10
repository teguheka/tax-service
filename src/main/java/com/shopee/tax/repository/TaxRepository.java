package com.shopee.tax.repository;

import com.shopee.tax.entity.Tax;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxRepository extends PagingAndSortingRepository<Tax, Long> {
    Optional<Tax> findByName(String name);
}
