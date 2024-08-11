package net.revature.RevShop.DTOs;

import java.util.List;
import java.util.ArrayList;

public class AIRequest {

    private String model;
    private List<AIMessage> messages;

    public AIRequest() {
    }

    public AIRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new AIMessage("user", prompt));
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<AIMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<AIMessage> messages) {
        this.messages = messages;
    }
}