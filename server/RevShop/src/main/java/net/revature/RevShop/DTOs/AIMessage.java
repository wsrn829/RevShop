package net.revature.RevShop.DTOs;

public class AIMessage {

    private String role;
    private String content;

    public AIMessage() {
    }

    public AIMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}