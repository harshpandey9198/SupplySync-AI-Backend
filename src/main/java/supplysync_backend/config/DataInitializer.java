package supplysync_backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import supplysync_backend.entity.Role;
import supplysync_backend.entity.User;
import supplysync_backend.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (!userRepository.existsByEmail("admin@supplysync.com")) {

            User admin = User.builder()

                    .name("Admin")

                    .email("admin@supplysync.com")

                    .password(passwordEncoder.encode("admin123"))

                    .role(Role.ADMIN)

                    .active(true)

                    .build();

            userRepository.save(admin);

            System.out.println("=======================================");
            System.out.println(" Default Admin User Created");
            System.out.println(" Email : admin@supplysync.com");
            System.out.println(" Password : admin123");
            System.out.println("=======================================");
        }

    }
}