package net.revature.RevShop.Controllers;

import net.revature.RevShop.DTOs.AIRequest;
import net.revature.RevShop.DTOs.AIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String aiUrl;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String chatbot(@RequestParam("prompt") String prompt) {
        AIRequest request = new  AIRequest(model, prompt);
        AIResponse response = template.postForObject(aiUrl, request, AIResponse.class);
        return response.getChoices().get(0).getMessage().getContent();
    }
}
