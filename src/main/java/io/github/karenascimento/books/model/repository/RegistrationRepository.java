package io.github.karenascimento.books.model.repository;

import io.github.karenascimento.books.model.biblioteca.BookRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<BookRegistration, Integer> {

}
