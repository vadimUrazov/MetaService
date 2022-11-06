package net.thumbtack.buscompany.dao.repository;

import net.thumbtack.buscompany.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Bus getBusByBusName(String name);
}
