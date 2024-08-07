package net.revature.RevShop.Controllers;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import net.revature.RevShop.DTOs.LoginRequest;
import net.revature.RevShop.DTOs.LoginResponse;
import net.revature.RevShop.Models.User;
import net.revature.RevShop.Security.JwtUtil;
import net.revature.RevShop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

    private final JwtUtil jwtUtil;


    private final UserService userService;
    @Autowired
    public AuthController(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest, HttpServletResponse response) throws Exception {
        User user = userService.findUserByUsername(authenticationRequest.getUsername());
        if(user != null) {
            if (!user.isActive()) {
                throw new Exception("User account is inactive");
            }

            if (userService.checkPassword(authenticationRequest.getPassword(), user.getPassword())) {
                final String jwt = jwtUtil.generateToken(user.getUsername());
                System.out.println("Generated JWT: " + jwt);
                Cookie cookie = new Cookie("Authentication", jwt);

                cookie.setHttpOnly(true);
                cookie.setPath("/");
                response.addCookie(cookie);

                return ResponseEntity.ok(new LoginResponse(jwt, user));
            } else {
                throw new Exception("Invalid credentials");
            }
        } else {
            throw new Exception("User is null.");
        }
    }
}