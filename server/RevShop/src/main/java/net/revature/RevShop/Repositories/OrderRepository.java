package net.revature.RevShop.Repositories;

import net.revature.RevShop.Models.Order;
import net.revature.RevShop.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByBuyer(User buyer);
}
