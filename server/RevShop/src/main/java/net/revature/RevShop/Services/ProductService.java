package net.revature.RevShop.Services;

import net.revature.RevShop.Models.Product;
import net.revature.RevShop.Repositories.ProductRepository;
import net.revature.RevShop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Product getProductById(Integer productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("no product found"));
    }

    public List<Product> getAllProducts(){
        return productRepository.getAllProducts();
    }

    public List<Product> getProductsBySellerName(String sellerName){
        return productRepository.getAllProductsBySellerName(sellerName);
    }

    public List<Product> getProductsByItemName(String itemName){
        return productRepository.getAllProductsByItemName(itemName);
    }
}
