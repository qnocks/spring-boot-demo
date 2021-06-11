package ru.rostanin.springbootdemo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rostanin.springbootdemo.domain.Meditation;

@Repository
public interface MeditationsRepository extends CrudRepository<Meditation, Long> {
}
