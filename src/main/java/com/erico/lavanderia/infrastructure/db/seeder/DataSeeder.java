package com.erico.lavanderia.infrastructure.db.seeder;

import com.erico.lavanderia.domain.scheduling.SchedulingRepository;
import com.erico.lavanderia.domain.user.Email;
import com.erico.lavanderia.domain.user.Password;
import com.erico.lavanderia.domain.user.User;
import com.erico.lavanderia.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class DataSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataSeeder.class);

    private final UserRepository userRepository;
    private final SchedulingRepository schedulingRepository;

    public DataSeeder(UserRepository userRepository, SchedulingRepository schedulingRepository) {
        this.userRepository = userRepository;
        this.schedulingRepository = schedulingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        var email = new Email("admin@test.com");

        userRepository.findByEmail(email).ifPresentOrElse(
                (u) -> logger.info("Usuário administrador configurado: {}", u.getEmail()),
                () -> {
                    logger.info("Configurando usuário administrador: {}", email);
                    User user = new User("Admin", email, new Password("@dm!n123"), "202010937", 1405);
                    userRepository.save(user);
                }
        );
    }
}
