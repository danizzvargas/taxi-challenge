package com.gbm.challenge.taxitracking.repository;

import com.gbm.challenge.taxitracking.entity.TaxiUser;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Data access for the taxi_user's table.
 *
 * @author danizz
 */
public interface TaxiUserRepository extends PagingAndSortingRepository<TaxiUser, Integer> {

}
