package io.github.karenascimento.books.rest;

import io.github.karenascimento.books.model.biblioteca.BookLoan;
import io.github.karenascimento.books.model.biblioteca.BookRegistration;
import io.github.karenascimento.books.model.biblioteca.Client;
import io.github.karenascimento.books.model.repository.BookLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class BookLoanController {

    private final BookLoanRepository loanRepository;

    @Autowired
    public BookLoanController(BookLoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookLoan saveLoan(@RequestBody BookLoan bookRegistration){
        BookLoan savedLoan = loanRepository.save(bookRegistration);

        return savedLoan;
    }

    @GetMapping("{id}")
    public BookLoan getLoanById(@PathVariable Integer id) {
        return loanRepository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public List<BookLoan> findAll() {
        return loanRepository.findAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLoans(@RequestBody List<BookLoan> updateLoans) {
        updateLoans.forEach(updateLoan -> {
            loanRepository.findById(updateLoan.getId())
                    .map(existingLoan -> {
                        existingLoan.setBook(updateLoan.getBook());
                        existingLoan.setClient(updateLoan.getClient());
                        existingLoan.setDataEmprestimo(updateLoan.getDataEmprestimo());
                        return loanRepository.save(existingLoan);
                    })
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empréstimo com ID " + updateLoan.getId() + " não encontrado"));
        });
    }



    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLoan(@PathVariable Integer id){
        loanRepository
                .findById(id)
                .map(loan -> {
                    loanRepository.delete(loan);
                    return Void.TYPE;
                });
    }
}
