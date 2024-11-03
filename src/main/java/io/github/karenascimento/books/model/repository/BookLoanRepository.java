package io.github.karenascimento.books.model.repository;

import io.github.karenascimento.books.model.biblioteca.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Integer> {
}
