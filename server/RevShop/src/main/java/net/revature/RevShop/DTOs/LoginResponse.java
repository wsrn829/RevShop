package net.revature.RevShop.DTOs;


import com.fasterxml.jackson.annotation.JsonProperty;
import net.revature.RevShop.Models.User;

public class LoginResponse {

    private String token;
    private User user;

    public LoginResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    @JsonProperty("Authentication")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}