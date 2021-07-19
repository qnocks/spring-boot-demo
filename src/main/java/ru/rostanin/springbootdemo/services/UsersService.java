package ru.rostanin.springbootdemo.services;

import org.springframework.beans.BeanUtils;
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

        User existingUser = getById(id);

        BeanUtils.copyProperties(user, existingUser, "id");

//        if (user.getLogin() != null) existingUser.setLogin(user.getLogin());
//        if (user.getPassword() != null) existingUser.setPassword(user.getPassword());
//        if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
//        if (user.getSubscription() != null) existingUser.setSubscription(user.getSubscription());

        usersRepository.save(existingUser);

        return existingUser;
    }

    public void delete(Long id) {
        usersRepository.deleteById(id);
    }
}
