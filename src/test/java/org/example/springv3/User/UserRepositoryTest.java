package org.example.springv3.User;

import org.example.springv3.user.User;
import org.example.springv3.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsernameAndPassword_test(){
        String username = "ssar";
        String password = "1234";
        User user = userRepository.findByUsernameAndPassword(username, password);
        System.out.println("user : " +user);
    }

}
