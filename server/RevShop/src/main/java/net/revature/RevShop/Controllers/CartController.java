package net.revature.RevShop.Controllers;

import net.revature.RevShop.Models.CartItem;
import net.revature.RevShop.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addProductToCart(@RequestParam Integer buyerId, @RequestParam Integer productId, @RequestParam Integer quantity) {
        CartItem cartItem = cartService.addProductToCart(buyerId, productId, quantity);
        return ResponseEntity.ok(cartItem);
    }
}
