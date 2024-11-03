package io.github.karenascimento.books.rest;

import io.github.karenascimento.books.model.biblioteca.Client;
import io.github.karenascimento.books.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    public ClientRepository repository;

    @Autowired
    public ClientController(ClientRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
   public Client save( @RequestBody Client client){
        return repository.save(client);
   }

    @GetMapping
    public List<Client> findAll() {
        return repository.findAll();
    }

   @GetMapping("{id}")
    public Client findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND)));
   }

   @DeleteMapping("{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void delete(@PathVariable Integer id){
        repository
                .findById(id)
                .map(client -> {
                    repository.delete(client);
                    return Void.TYPE;
                });
   }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Client updateClient) {
        repository
                .findById(id)
                .map(client -> {
                    updateClient.setId(client.getId());
                    return repository.save(updateClient);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
