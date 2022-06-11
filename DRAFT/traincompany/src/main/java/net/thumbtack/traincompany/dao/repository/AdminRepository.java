package net.thumbtack.traincompany.dao.repository;

import net.thumbtack.traincompany.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}

