package ru.rostanin.springbootdemo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.rostanin.springbootdemo.domain.Meditation;
import ru.rostanin.springbootdemo.repositories.MeditationsRepository;

import java.util.List;

@Service
public class MeditationsService {

    private static final Logger logger = LoggerFactory.getLogger(MeditationsRepository.class);

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
        Meditation existingMeditation = getById(id);

        if (meditation.getTitle() != null) existingMeditation.setTitle(meditation.getTitle());
        if (meditation.getDescription() != null) existingMeditation.setDescription(meditation.getDescription());
        if (meditation.getSourcePath() != null) existingMeditation.setSourcePath(meditation.getSourcePath());
        if (meditation.getRating() != null) existingMeditation.setRating(meditation.getRating());

        meditationsRepository.save(existingMeditation);

        return existingMeditation;
    }

    public void delete(Long id) {
        meditationsRepository.deleteById(id);
    }
}
