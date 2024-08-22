package net.engineeringdigest.journalApp.repoistory;

import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTests {

    @Autowired
    private UserRepositoryImpl userRepository;

    //@Disabled("tested")
    @Test
    void testSaveNewUser() {
        userRepository.getUserForSA();
    }
}
