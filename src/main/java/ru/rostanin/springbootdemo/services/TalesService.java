package ru.rostanin.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rostanin.springbootdemo.domain.Tale;
import ru.rostanin.springbootdemo.domain.User;
import ru.rostanin.springbootdemo.repositories.TalesRepository;
import ru.rostanin.springbootdemo.repositories.UsersRepository;

import java.util.List;

@Service
public class TalesService {

    private final TalesRepository talesRepository;

    @Autowired
    public TalesService(TalesRepository talesRepository) {
        this.talesRepository = talesRepository;
    }

    public List<Tale> getAll() {
        return (List<Tale>) talesRepository.findAll();
    }

    public Tale getById(Long id) {
        return talesRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Tale create(Tale tale) {
        return talesRepository.save(tale);
    }

    public Tale update(Long id, Tale tale) {
        return talesRepository.save(tale);
    }

    public void delete(Long id) {
        talesRepository.deleteById(id);
    }
}
