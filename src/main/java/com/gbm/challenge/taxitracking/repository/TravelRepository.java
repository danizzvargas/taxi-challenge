package com.gbm.challenge.taxitracking.repository;

import com.gbm.challenge.taxitracking.entity.Travel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Data access for the travel's table.
 *
 * @author danizz
 */
public interface TravelRepository extends PagingAndSortingRepository<Travel, Integer> {

}
