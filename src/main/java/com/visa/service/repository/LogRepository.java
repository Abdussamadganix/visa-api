package com.visa.service.repository;

import com.visa.service.model.entity.Log;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Abdussamad
 */
@Repository
public interface LogRepository extends PagingAndSortingRepository<Log, Long> {

}
