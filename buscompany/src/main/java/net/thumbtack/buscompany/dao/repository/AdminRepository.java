package net.thumbtack.buscompany.dao.repository;

import net.thumbtack.buscompany.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}

