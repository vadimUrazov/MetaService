package net.thumbtack.buscompany.dao.repository;

import java.util.List;
import net.thumbtack.buscompany.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

  void deleteById(long id);


  List<Trip> getAllByApprovedTrue();

  Trip getTripById(long id);

  List<Trip> getTripsByFromStation(String fromStation);

  List<Trip> getTripsByToStation(String toStation);
}
