package com.example.books.service.impl;

import com.example.books.model.dto.BookDTO;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return null;
                //bookRepository.findAll();
    }
}
