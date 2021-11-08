package com.example.books.service;

import com.example.books.model.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDTO> getBooks();

    Optional<BookDTO> getBookById(Long id);

    void deleteBookById(Long id);

    long createBook(BookDTO bookDTO);

    Long updateBook(BookDTO bookDTO);

    Page<BookDTO> getBooks(Integer pageNo, Integer pageSize, String sortBy);
}