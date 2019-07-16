package com.visa.service.repository;

import com.visa.service.model.entity.Token;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Abdussamad
 */
@Repository
public interface TokenRepository extends PagingAndSortingRepository<Token, Long> {

  Token findOneByToken(String token);

  Token findOneByUserAndToken(String userKey, String token);
}
