package com.example.springintro.service;

import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBook() throws IOException;

    List<Book> findAllBooksAfterYear (int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorOrderByReleaseDate(String firstName, String lastName);
}
