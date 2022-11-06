package net.thumbtack.traincompany.dao.repository;

import net.thumbtack.traincompany.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByLogin(String login);

    User getUserById(long id);


}
