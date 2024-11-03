package io.github.karenascimento.books.model.repository;

import io.github.karenascimento.books.model.biblioteca.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
