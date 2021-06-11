package ru.rostanin.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rostanin.springbootdemo.domain.Meditation;
import ru.rostanin.springbootdemo.domain.Tale;
import ru.rostanin.springbootdemo.repositories.MeditationsRepository;
import ru.rostanin.springbootdemo.repositories.TalesRepository;

import java.util.List;

@Service
public class MeditationsService {

    private final MeditationsRepository meditationsRepository;

    @Autowired
    public MeditationsService(MeditationsRepository meditationsRepository) {
        this.meditationsRepository = meditationsRepository;
    }

    public List<Meditation> getAll() {
        return (List<Meditation>) meditationsRepository.findAll();
    }

    public Meditation getById(Long id) {
        return meditationsRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Meditation create(Meditation meditation) {
        return meditationsRepository.save(meditation);
    }

    public Meditation update(Long id, Meditation meditation) {
        return meditationsRepository.save(meditation);
    }

    public void delete(Long id) {
        meditationsRepository.deleteById(id);
    }
}
