package net.thumbtack.buscompany.dao.repository;

import net.thumbtack.buscompany.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByLogin(String login);

    User getUserById(long id);


}
