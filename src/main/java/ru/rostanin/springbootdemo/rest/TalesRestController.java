package ru.rostanin.springbootdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rostanin.springbootdemo.domain.Meditation;
import ru.rostanin.springbootdemo.domain.Tale;
import ru.rostanin.springbootdemo.repositories.TalesRepository;
import ru.rostanin.springbootdemo.services.MeditationsService;
import ru.rostanin.springbootdemo.services.TalesService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tales")
public class TalesRestController {

    private final TalesService talesService;

    @Autowired
    public TalesRestController(TalesService talesService) {
        this.talesService = talesService;
    }

    @GetMapping
    public ResponseEntity<List<Tale>> list() {
        return new ResponseEntity<>(talesService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Tale> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(talesService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tale> create(@RequestBody Tale tale) {
        return new ResponseEntity<>(talesService.create(tale), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Tale> update(@PathVariable("id") Long id, @RequestBody Tale tale) {
        return new ResponseEntity<>(talesService.update(id, tale), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        talesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
