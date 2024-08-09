package net.revature.RevShop.Controllers;

import net.revature.RevShop.Models.Product;
import net.revature.RevShop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product>name = productService.getAllProducts();
        return ResponseEntity.ok(name);
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProduct2(){
        List<Product>name = productService.getAllProducts();
        return name;
    }
    @GetMapping("/seller")
    public ResponseEntity<List<Product>> getAllProductBySellerName(@RequestBody String sellerName){
        return ResponseEntity.ok(productService.getProductsBySellerName(sellerName));
    }

    @GetMapping("/keyword")
    public ResponseEntity<List<Product>> getAllProductByItemName(@RequestBody String itemName){
        return ResponseEntity.ok(productService.getProductsByItemName(itemName));
    }
}
