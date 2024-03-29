package com.visa.service.repository;

import com.visa.service.model.entity.MerchantAlias;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Abdussamad
 */
@Repository
public interface MerchantAliasRepository extends PagingAndSortingRepository<MerchantAlias, Long>,
    QueryDslPredicateExecutor<MerchantAlias> {

  MerchantAlias findOneByAliasId(String aliasId);

  MerchantAlias findOneByTerminalId(String terminalId);
}
