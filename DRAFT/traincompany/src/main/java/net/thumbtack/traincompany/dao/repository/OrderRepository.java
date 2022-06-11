package net.thumbtack.traincompany.dao.repository;

import net.thumbtack.traincompany.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query("UPDATE DayTrip SET freeCount = freeCount - :count WHERE freeCount >=:count")
    int updateDayTrip(@Param("count") int count);

    Order getOrderById(long id);
}
