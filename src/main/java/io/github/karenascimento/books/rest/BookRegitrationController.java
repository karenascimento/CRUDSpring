package io.github.karenascimento.books.rest;


import io.github.karenascimento.books.model.biblioteca.BookRegistration;
import io.github.karenascimento.books.model.biblioteca.Client;
import io.github.karenascimento.books.model.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.w3c.dom.html.HTMLElement;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRegitrationController {

    public RegistrationRepository repository; // campo repository do tipo RegistrationRepository em uma classe Spring

    @Autowired
    public BookRegitrationController(RegistrationRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookRegistration save(@RequestBody BookRegistration bookRegistration){
        return repository.save(bookRegistration);
    }

    @GetMapping("{id}")
    public BookRegistration findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public List<BookRegistration> findAll() {
        return repository.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository
                .findById(id)
                .map(bookRegistration -> {
                    repository.delete(bookRegistration);
                    return Void.TYPE;
                });
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody BookRegistration updatedBookRegistration) {
        repository
                .findById(id)
                .map(bookRegistration -> {
                    updatedBookRegistration.setId(bookRegistration.getId());
                    return repository.save(updatedBookRegistration);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }



}
