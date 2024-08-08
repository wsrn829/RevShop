package net.revature.RevShop.Controllers;

import net.revature.RevShop.Models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/products")
//@CrossOrigin(
//        origins = {"http://localhost:3000"},
//        allowCredentials = "true"
//)
public class ProductController {

    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
        // TODO: service for get product & ProductDto
    }
}
