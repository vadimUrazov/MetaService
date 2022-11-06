package net.thumbtack.shipcompany.dao.repository;

import net.thumbtack.shipcompany.entity.DayTrip;
import net.thumbtack.shipcompany.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DayTripRepository extends JpaRepository<DayTrip, Long> {

    DayTrip getDayTripByDateAndAndTrip(LocalDate date, Trip trip);

    DayTrip getDayTripById(long id);
}
