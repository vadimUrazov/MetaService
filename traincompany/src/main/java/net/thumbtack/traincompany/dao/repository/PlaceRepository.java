package net.thumbtack.traincompany.dao.repository;

import net.thumbtack.traincompany.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> getPlacesByPassengerNull();

}
