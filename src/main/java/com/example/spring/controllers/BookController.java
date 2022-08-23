package com.example.spring.controllers;

import com.example.spring.entities.Book;
import com.example.spring.services.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
        this.bookService.addBook(new Book(null,"el tunel","pepe",23.2, LocalDate.of(2002,2,3),false));
        this.bookService.addBook(new Book(null,"Papasito","tomas",23.2, LocalDate.of(2002,2,3),true));
        this.bookService.addBook(new Book(null,"El principito","Sant De",23.2, LocalDate.of(2002,2,3),false));

    }
    //RestController, para trabajar con json, @Controller, permite compartir vistas HTMl entre controladores
    //peticion http get para traer todos los libros
    @Value("${app.message}")
    String message;

    @GetMapping("/api/books")
    public List<Book> findAll(){

        System.out.println(message);
        return bookService.getAll();
    }

    //peticion http get para traer unicamente un libro teniendo en cuenta su id
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> getOneByID(@PathVariable Long id){
        return bookService.getOneByID(id);
    }

    //peticion http post para agregar un libro a nuestra bd
    @PostMapping("/api/books")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    //peticion http post para actualizar un libro dependiendo del id

    @PutMapping("/api/books")
    @ApiOperation("This method update a book")
    public ResponseEntity<Book> updateBook(@ApiParam("Book param para actualizar")@RequestBody Book book){
        return bookService.updateBook(book);
    }

    //Peticion http delete para eleminar un libro de la bd

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id);
    }
}
