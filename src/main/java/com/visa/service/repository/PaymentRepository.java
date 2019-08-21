package com.visa.service.repository;

import com.visa.service.model.entity.Payment;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by abdussamad.olaiya on 10/07/2019.
 */
@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long>,
    QueryDslPredicateExecutor<Payment> {

  Payment findOneByUniqueKey(String uniqueKey);

}
