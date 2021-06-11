package ru.rostanin.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rostanin.springbootdemo.domain.User;
import ru.rostanin.springbootdemo.repositories.UsersRepository;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> getAll() {
        return (List<User>) usersRepository.findAll();
    }

    public User getById(Long id) {
        return usersRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public User create(User user) {
        return usersRepository.save(user);
    }

    public User update(Long id, User user) {
        return usersRepository.save(user);
    }

    public void delete(Long id) {
        usersRepository.deleteById(id);
    }
}
