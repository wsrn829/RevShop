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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    //setup Mock Injeact mock
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;
    private Long MyIdL = 1L;

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
        when(userService.createUser(any(User.class))).thenReturn(user);
        when(userService.findUserByUsername("YuQi")).thenReturn(null);

        ResponseEntity<User> response = userController.createUser(user);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("YuQi", response.getBody().getUsername());
    }

    @Test
    public void testRegisterUserAlreadyExists() {
        when(userService.findUserByUsername("YuQi")).thenReturn(user);

        ResponseEntity<User> response = userController.createUser(user);

        assertEquals(400, response.getStatusCodeValue());
    }
}
