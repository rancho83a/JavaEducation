package com.example.books.service;

import com.example.books.model.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
}
