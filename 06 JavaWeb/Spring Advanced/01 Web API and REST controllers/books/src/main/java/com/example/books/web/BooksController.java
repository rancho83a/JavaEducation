package com.example.books.web;

import com.example.books.model.dto.BookDTO;
import com.example.books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

 private final BookService bookService;

 public BooksController(BookService bookService) {
  this.bookService = bookService;
 }

 public ResponseEntity<List<BookDTO>> getAllBooks(){

  List<BookDTO> allBooks = this.bookService.getAllBooks();

  return ResponseEntity.ok(allBooks);
 }

}
