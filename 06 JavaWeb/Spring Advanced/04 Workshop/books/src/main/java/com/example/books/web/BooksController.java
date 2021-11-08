package com.example.books.web;

import com.example.books.model.dto.BookDTO;
import com.example.books.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> allBooks = this.bookService.getBooks();
        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id) {
        Optional<BookDTO> bookById = this.bookService.getBookById(id);

        if (bookById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookById.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> delete(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO,
                                          UriComponentsBuilder uriBuilder) {

        long bookId = this.bookService.createBook(bookDTO);

        URI location = uriBuilder.path("books/{id}").buildAndExpand(bookId).toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(
            @PathVariable("id") Long id,
            @RequestBody BookDTO bookDTO,
            UriComponentsBuilder uriBuilder
    ) {
        Long bookId = this.bookService.updateBook(bookDTO);

        URI location = uriBuilder.path("books/{id}").buildAndExpand(bookId).toUri();

        return bookId == null ? ResponseEntity.notFound().build()
                : ResponseEntity
                .created(location)
                .build();

    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<BookDTO>> getAllBooks(
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "3") Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(
                this.bookService.getBooks(pageNo, pageSize, sortBy));
    }
}


