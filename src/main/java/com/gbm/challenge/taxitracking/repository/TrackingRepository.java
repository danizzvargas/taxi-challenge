package com.gbm.challenge.taxitracking.repository;

import com.gbm.challenge.taxitracking.entity.Tracking;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Data access for the tracking's table.
 *
 * @author danizz
 */
public interface TrackingRepository extends PagingAndSortingRepository<Tracking, Integer> {

    @Query("SELECT tr FROM Tracking tr WHERE tr.idTravel.idTaxiUser.phone = ?1")
    public List<Tracking> findLastLocation(String phone, Pageable page);
}
