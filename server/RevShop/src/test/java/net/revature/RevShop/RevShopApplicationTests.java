package net.revature.RevShop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.revature.RevShop.Controllers.UserController;
import net.revature.RevShop.Models.User;
import net.revature.RevShop.Services.CustomUserDetailsService;
import net.revature.RevShop.Services.UserService;
import net.revature.RevShop.Security.JwtUtil;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest

class RevShopApplicationTests {

	
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
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        classUnderTest = new User(
                "YuQi",
                "Yuqi@Gidle.com",
                "Freak",
                "Yuqi",
                "Song",
                User.UserType.BUYER,
                "Test Business",
                false,
                LocalDateTime.now()
        );

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
                "\"banned\":" + classUnderTest.isBanned() +
                "}";
        System.out.println(requestBody);
        //  request user registration
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:7777/api/users"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Send request
        HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
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
        assertEquals("Yuqi@Gidle.com", createdUser.getEmail());
        assertEquals("Yuqi", createdUser.getFirstName());
        assertEquals("Song", createdUser.getLastName());
        assertEquals(User.UserType.BUYER, createdUser.getType());
        assertEquals("Test Business", createdUser.getBusinessDetails());
        assertEquals(false, createdUser.isBanned());
    }

}
