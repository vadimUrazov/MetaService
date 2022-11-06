package net.thumbtack.shipcompany.dao.repository;

import net.thumbtack.shipcompany.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> getPlacesByPassengerNull();

}
