package net.revature.RevShop.Services;

import net.revature.RevShop.Models.CartItem;
import net.revature.RevShop.Models.Product;
import net.revature.RevShop.Models.User;
import net.revature.RevShop.Repositories.CartRepository;
import net.revature.RevShop.Repositories.ProductRepository;
import net.revature.RevShop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public CartItem addProductToCart(Integer buyerId, Integer productId, Integer quantity) {
        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setBuyer(buyer);
        cartItem.setSeller(product.getSeller());
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        return cartRepository.save(cartItem);
    }
    public List<CartItem> viewCart(){
        return cartRepository.findAll();
    }

    public void removeProductFromCart(Integer cartItemId){
        CartItem product = cartRepository.findByCartItemId(cartItemId);

        if(product == null){
            System.out.println("Product is not found in the cart.");
            throw new RuntimeException("Product is not found in the cart.");
        }else{
            cartRepository.deleteByCartItemId(cartItemId);
        }

    }
}
