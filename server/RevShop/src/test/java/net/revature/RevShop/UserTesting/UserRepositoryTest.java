package net.revature.RevShop.UserTesting;

import net.revature.RevShop.Models.User;
import net.revature.RevShop.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserRepositoryTest {
    //mock prep mock , inject mock and before each
    @Mock
    private UserRepository userRepository;
    private User user;
    Long MyIdL = 1L;

    @BeforeEach
    public void setUp() {
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


    //All testing
    @Test
    public void testingSaveUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertEquals(user.getUsername(), savedUser.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testingFindUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        Optional<User> foundUser = userRepository.findById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals(user.getUsername(), foundUser.get().getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testingFindUserByUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(user);

        User foundUser = userRepository.findByUsername("YuQi");

        assertNotNull(foundUser);
        assertEquals(user.getUsername(), foundUser.getUsername());
        verify(userRepository, times(1)).findByUsername("YuQi");
    }

    @Test
    public void testingDeleteUser() {
        doNothing().when(userRepository).deleteById(anyLong());

        userRepository.deleteById(MyIdL);

        verify(userRepository, times(1)).deleteById(MyIdL);
    }

}