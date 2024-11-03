package io.github.karenascimento.books.model.biblioteca;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.*;

@Getter@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 155)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column
    private String email;

    @Column
    private String telefone;

}
