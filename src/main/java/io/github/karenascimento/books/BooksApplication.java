package io.github.karenascimento.books;

import io.github.karenascimento.books.model.biblioteca.Client;
import io.github.karenascimento.books.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication //classe inicializa a aplicação
public class BooksApplication {

    @Bean
    public CommandLineRunner run(@Autowired ClientRepository repository){
        return args ->{
            Client cliente = Client.builder().cpf("00000000000").nome("Ana").build(); // gera uma instancia de clientes com esses dados
            repository.save(cliente);
        };
    }

    public static void main(String[] args) {


        SpringApplication.run(BooksApplication.class, args);
    }
}
