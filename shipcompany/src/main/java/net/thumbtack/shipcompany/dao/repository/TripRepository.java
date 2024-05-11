package net.thumbtack.shipcompany.dao.repository;

import net.thumbtack.shipcompany.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    void deleteById(long id);


    List<Trip> getAllByApprovedTrue();

    Trip getTripById(long id);

    Trip getTripByFromStationAndAndToStation(String fromStation, String toStation);
}
