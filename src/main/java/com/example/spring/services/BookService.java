package com.example.spring.services;

import com.example.spring.entities.Book;
import com.example.spring.repositories.BookRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;



@Service
public class BookService {

    private final Logger log = LoggerFactory.getLogger(BookService.class);
    private final BookRepository repository;

    public BookService(BookRepository bookRepository){
        this.repository = bookRepository;
    }
    //private List<Book> books = new ArrayList<>();

    /**
     * This method return all books of our bd
     * @return List of books
     * */
    public List<Book> getAll(){

        return repository.findAll();
    }

    /**
     * This method create a book in our bd
     * @param book
     * @return ResponseEntity<Book>
     */

    public ResponseEntity<Book> addBook(Book book){

        if(book.getId() == null){
            repository.save(book);
            log.info("Create book suscefull");
            return ResponseEntity.ok().build();
        }
        //log, son muy importantes
        log.warn("The book already exist in our base data");
        return ResponseEntity.badRequest().build();
    }

    /**
     * This method is to get book by id
     * @param id
     * @return ResponseEntity<Book>
     */
    public ResponseEntity<Book> getOneByID(Long id){
        Optional<Book> result= repository.findById(id);
        //result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.notFound().build();
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        /*if(result.isPresent()){
            return result.get();
        }
        return null;
        */
        //return result.orElse(null);En caso de retornar un libro no podriamos enviar una correcta respuesta en
        //caso de que el libro no exista por lo que estarmiamos retornarno un null, por esto es mejor usar ResponseEntitie
    }

    public ResponseEntity<Book> updateBook(Book book){

        if(repository.existsById(book.getId())){
            repository.save(book);
            log.info("Book update Ok");
            return ResponseEntity.ok().build();
        }
        log.warn("Trying to update a non existent book");

        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Book> deleteBook(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            log.info("Book delete OK");
            return ResponseEntity.ok().build();
        }
        log.warn("Trying to delete a non existent book");

        return ResponseEntity.badRequest().build();
    }
}