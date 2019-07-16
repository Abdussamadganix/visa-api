package com.visa.service.repository;

import com.visa.service.model.entity.Log;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Abdussamad
 */
public interface LogRepository extends PagingAndSortingRepository<Log, Long> {

}
