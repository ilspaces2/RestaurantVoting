package ru.javaops.bootjava;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javaops.bootjava.model.Role;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.repository.UserRepository;

import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class RestaurantVotingApplication implements ApplicationRunner {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantVotingApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(new User("e@mail.com", "0", "0", "1234", Set.of(Role.ROLE_ADMIN, Role.ROLE_USER)));
        for (int i = 0; i < 100; i++) {
            userRepository.save(new User(i + "@mail.com", "1", "2", "3", Set.of(Role.ROLE_USER)));
        }
        System.out.println(userRepository.findAll());
    }
}
