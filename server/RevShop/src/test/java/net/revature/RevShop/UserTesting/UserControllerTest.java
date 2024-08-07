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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    //setup Mock Injeact mock
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;
    private Long MyIdL = 1L;
    private MockMvc mockMvc ;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserId(MyIdL);
        user.setUsername("YuQi");
        user.setPassword("Freak");
    }

    //testing
    @Test
    public void testRegisterUser() throws Exception {
        when(userService.findUserByUsername("YuQi")).thenReturn(null);
        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"YuQi\", \"password\": \"Freak\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("YuQi"));
    }


}
