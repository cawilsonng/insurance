package com.wilson.insurance.repositories;

import com.wilson.insurance.models.entity.Quotation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends CrudRepository<Quotation, Long> {

}
