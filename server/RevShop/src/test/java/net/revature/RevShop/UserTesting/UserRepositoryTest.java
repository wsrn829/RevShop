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
    private User classUndertest;
    Long MyIdL = 1L;

    @BeforeEach
    public void setUp() {
        classUndertest = new User(
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
    }

    //All testing
    @Test
    public void testingSaveUser() {
        when(userRepository.save(any(User.class))).thenReturn(classUndertest);

        User savedUser = userRepository.save(classUndertest);

        assertNotNull(savedUser);
        assertEquals(classUndertest.getUsername(), savedUser.getUsername());
        verify(userRepository, times(1)).save(classUndertest);
    }

    @Test
    public void testingFindUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(classUndertest));

        Optional<User> userFound = userRepository.findById(1L);

        assertTrue(userFound.isPresent());
        assertEquals(classUndertest.getUsername(), userFound.get().getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testingFindUserByUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(classUndertest);

        User userFound = userRepository.findByUsername("YuQi");

        assertNotNull(userFound);
        assertEquals(classUndertest.getUsername(), userFound.getUsername());
        verify(userRepository, times(1)).findByUsername("YuQi");
    }

    @Test
    public void testingDeleteUser() {
        doNothing().when(userRepository).deleteById(anyLong());

        userRepository.deleteById(MyIdL);

        verify(userRepository, times(1)).deleteById(MyIdL);
    }

}
