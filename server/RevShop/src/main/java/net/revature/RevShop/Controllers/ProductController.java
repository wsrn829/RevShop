package net.revature.RevShop.Controllers;

import net.revature.RevShop.Models.Product;
import net.revature.RevShop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/products")
//@CrossOrigin(
//        origins = {"http://localhost:3000"},
//        allowCredentials = "true"
//)
public class ProductController {

    @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable("product_id") Integer productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }
}
