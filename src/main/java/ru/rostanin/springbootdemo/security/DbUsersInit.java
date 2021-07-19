package ru.rostanin.springbootdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.rostanin.springbootdemo.domain.User;
import ru.rostanin.springbootdemo.repositories.UsersRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class DbUsersInit implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DbUsersInit(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("user", passwordEncoder.encode("user"), UserRole.USER.name(),
                "READ_DOCTORS,READ_PATIENTS,READ_APPOINTMENTS,WRITE_APPOINTMENT");

        User admin = new User("admin", passwordEncoder.encode("admin"), "ADMIN",
                "READ_DOCTORS,WRITE_DOCTOR,EDIT_DOCTOR," +
                        "READ_PATIENTS,WRITE_PATIENT,EDIT_PATIENT," +
                        "READ_APPOINTMENTS,WRITE_APPOINTMENT,EDIT_APPOINTMENT");

        List<User> users = Arrays.asList(user, admin);

        usersRepository.saveAll(users);
    }}
