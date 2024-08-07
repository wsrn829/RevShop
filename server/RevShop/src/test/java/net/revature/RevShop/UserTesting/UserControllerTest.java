package net.revature.RevShop.UserTesting;

import net.revature.RevShop.Controllers.UserController;
import net.revature.RevShop.Models.User;
import net.revature.RevShop.Services.UserService;
import net.revature.RevShop.Security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    //setup Mock Injeact mock
    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;
    private Long MyIdL = 1L;

    @Autowired
    private MockMvc mockMvc ;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(
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
    }

    //testing
    @Test
    public void testRegisterUser() throws Exception {
        when(userService.findUserByUsername("YuQi")).thenReturn(null);
        when(userService.createUser(any(User.class))).thenReturn(user);

        //https://stackoverflow.com/questions/72115234/mockmvc-integration-test-create-user-before-unit-test
        //https://mkyong.com/spring-boot/spring-test-how-to-test-a-json-array-in-jsonpath/
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"YuQi\", \"password\": \"Freak\", \"email\": \"yuqi@example.com\", " +
                                "\"firstName\": \"Yu\", \"lastName\": \"Qi\", \"type\": \"CUSTOMER\", \"businessDetails\": \"Some business details\", " +
                                "\"isActive\": true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("YuQi"))
                .andExpect(jsonPath("$.email").value("yuqi@example.com"))
                .andExpect(jsonPath("$.firstName").value("Yu"))
                .andExpect(jsonPath("$.lastName").value("Qi"))
                .andExpect(jsonPath("$.type").value("CUSTOMER"))
                .andExpect(jsonPath("$.businessDetails").value("Some business details"))
                .andExpect(jsonPath("$.isActive").value(true));
    }


}
