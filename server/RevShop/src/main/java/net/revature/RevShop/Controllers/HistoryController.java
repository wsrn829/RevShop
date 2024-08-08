package net.revature.RevShop.Controllers;

import net.revature.RevShop.Models.User;
import net.revature.RevShop.Security.JwtUtil;
import net.revature.RevShop.Services.OrderService;
import net.revature.RevShop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/history")
@CrossOrigin
public class HistoryController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@CookieValue("Authentication") String token) {
        //Get ID from token
        Integer id = jwtUtil.extractId(token);

        if (id == null) {
            return ResponseEntity.status(400).body("Bad auth cookie");
        }

        Optional<User> u = userService.getUserById(id);

        if (!u.isPresent()) {
            return ResponseEntity.status(400).body("Auth cookie is for expired user");
        }

        return ResponseEntity.ok(orderService.findByUser(u.get()));

    }

}
