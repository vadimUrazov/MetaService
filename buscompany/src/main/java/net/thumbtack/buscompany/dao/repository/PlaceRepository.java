package net.thumbtack.buscompany.dao.repository;

import java.util.List;
import net.thumbtack.buscompany.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

  List<Place> getPlacesByPassengerNull();

}
