package net.revature.RevShop.Repositories;

import net.revature.RevShop.Models.CartItem;
import net.revature.RevShop.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByBuyer(User buyer);
    CartItem findByCartItemId(Integer cartItemId);

    void deleteByCartItemId(Integer cartItemId);
}
