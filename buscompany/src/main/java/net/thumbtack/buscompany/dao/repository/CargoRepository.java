package net.thumbtack.buscompany.dao.repository;

import net.thumbtack.buscompany.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
