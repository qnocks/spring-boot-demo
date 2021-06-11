package ru.rostanin.springbootdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rostanin.springbootdemo.domain.User;
import ru.rostanin.springbootdemo.services.UsersService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersRestController {

    private final UsersService usersService;

    @Autowired
    public UsersRestController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<User>> list() {
        return new ResponseEntity<>(usersService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(usersService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(usersService.create(user), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User user) {
        return new ResponseEntity<>(usersService.update(id, user), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        usersService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
