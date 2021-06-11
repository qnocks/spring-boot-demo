package ru.rostanin.springbootdemo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rostanin.springbootdemo.domain.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
}
