package net.revature.RevShop.Services;

import net.revature.RevShop.Models.Order;
import net.revature.RevShop.Models.User;
import net.revature.RevShop.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    //get
    public List<Order> findByUser(User u) {

        return orderRepository.findByBuyer(u);
    }
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    //set

}
