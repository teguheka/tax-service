package com.shopee.tax.repository;

import com.shopee.tax.entity.Tax;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends PagingAndSortingRepository<Tax, Long> {

}
