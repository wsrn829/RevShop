package net.revature.RevShop.UserTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.revature.RevShop.Controllers.UserController;
import net.revature.RevShop.Models.User;
import net.revature.RevShop.Services.CustomUserDetailsService;
import net.revature.RevShop.Services.UserService;
import net.revature.RevShop.Security.JwtUtil;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

    //setup Mock Injeact mock
    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserService userService;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @InjectMocks
    private UserController userController;
    private ObjectMapper objectMapper;
    private HttpClient webClient;
    private User classUnderTest;
    private Long MyIdL = 1L;

    @Autowired
    private MockMvc mockMvc ;


    @BeforeAll
    public static void setUpAll() {
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        classUnderTest = new User(
                "YuQi",
                "Yuqi@Gidle.com",
                "Freak",
                "Yuqi",
                "Song",
                User.UserType.BUYER,
                "Test Business",
                true,
                LocalDateTime.now()
        );

        objectMapper = new ObjectMapper();
        webClient = HttpClient.newHttpClient();
    }


    @AfterEach
    public void tearDown() {
        webClient = null;
        objectMapper = null;
    }





    //testing
    @Test
    public void testRegisterUser() throws Exception {
        // Arrange
        when(userService.findUserByUsername("YuQi")).thenReturn(null);
        when(userService.createUser(any(User.class))).thenReturn(classUnderTest);

        String requestBody = "{" +
                "\"username\":\"" + classUnderTest.getUsername() + "\", " +
                "\"password\":\"" + classUnderTest.getPassword() + "\", " +
                "\"email\":\"" + classUnderTest.getEmail() + "\", " +
                "\"firstName\":\"" + classUnderTest.getFirstName() + "\", " +
                "\"lastName\":\"" + classUnderTest.getLastName() + "\", " +
                "\"type\":\"" + classUnderTest.getType().toString() + "\", " +
                "\"businessDetails\":\"" + classUnderTest.getBusinessDetails() + "\", " +
                "\"isActive\":" + classUnderTest.isActive() +
                "}";

        //  request user registration
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:7777/api/users"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Send request
        HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());

        // Check status code
        User createdUser = new User();
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            createdUser = objectMapper.readValue(response.body(), User.class);
        } else {
            System.out.println("Error: " + response.statusCode());
            throw new RuntimeException("Error: " + response.statusCode());
        }

        // Assert
        assertEquals(200, response.statusCode());
        assertEquals("YuQi", createdUser.getUsername());
        assertEquals("yuqi@example.com", createdUser.getEmail());
        assertEquals("YuQi", createdUser.getFirstName());
        assertEquals("Song", createdUser.getLastName());
        assertEquals(User.UserType.BUYER, createdUser.getType());
        assertEquals("Test Business", createdUser.getBusinessDetails());
        assertEquals(true, createdUser.isActive());
    }

}
