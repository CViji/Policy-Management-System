package com.system.user.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserRepositoryTest {

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testExistsByEmail() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        assertTrue(userRepository.existsByEmail("john@example.com"));
        
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        assertFalse(userRepository.existsByEmail("alice@example.com"));
    }
}
