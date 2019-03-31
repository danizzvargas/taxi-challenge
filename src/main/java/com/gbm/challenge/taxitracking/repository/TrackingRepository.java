package com.gbm.challenge.taxitracking.repository;

import com.gbm.challenge.taxitracking.entity.Tracking;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Data access for the tracking's table.
 *
 * @author danizz
 */
public interface TrackingRepository extends PagingAndSortingRepository<Tracking, Integer> {

}
