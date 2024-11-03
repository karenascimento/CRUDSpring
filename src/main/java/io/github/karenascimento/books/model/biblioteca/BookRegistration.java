package io.github.karenascimento.books.model.biblioteca;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter

@Entity
public class BookRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 155)
    private String titulo;
    private String autor;
    private String editora;

    @Column
    private LocalDate ano;

}
