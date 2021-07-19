package ru.rostanin.springbootdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rostanin.springbootdemo.domain.Meditation;
import ru.rostanin.springbootdemo.services.MeditationsService;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@RestController
@RequestMapping("/api/v1/meditations")
@CrossOrigin(origins = "http://localhost:3000/")
public class MeditationsRestController {

    private final MeditationsService meditationsService;

    @Autowired
    public MeditationsRestController(MeditationsService meditationsService) {
        this.meditationsService = meditationsService;
    }

    @GetMapping
    public ResponseEntity<List<Meditation>> list() {
        return new ResponseEntity<>(meditationsService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Meditation> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(meditationsService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Meditation> create(@RequestBody Meditation meditation) {
        return new ResponseEntity<>(meditationsService.create(meditation), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Meditation> update(@PathVariable("id") Long id, @RequestBody Meditation meditation) {
        return new ResponseEntity<>(meditationsService.update(id, meditation), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        meditationsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
