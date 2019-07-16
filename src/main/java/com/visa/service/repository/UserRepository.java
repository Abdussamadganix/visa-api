package com.visa.service.repository;

import com.visa.service.model.entity.User;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Abdussamad
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
    QueryDslPredicateExecutor<User> {

  User findOneByUniqueKey(String userKey);

  User findOneByEmail(String email);

  User findOneByPhone(String phone);
}
