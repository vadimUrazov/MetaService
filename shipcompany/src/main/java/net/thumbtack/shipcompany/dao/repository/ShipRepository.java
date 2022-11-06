package net.thumbtack.shipcompany.dao.repository;

import net.thumbtack.shipcompany.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    Ship getShipByShipName(String name);
}
