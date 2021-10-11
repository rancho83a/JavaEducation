package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final CategoryService categoryService;

    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

      //Please, delete Comments to check specific task

        printAllBooksAfter2000(2000);
        //printAllAuthorsWithBooksWithReleaseDateBeforeYear(1990);
        //printAllAuthorsOrderedByCountOfTheirBooks();
       // printAllBooksFromAuthorOderByReleaseDate("George", "Powell");

    }

    private void printAllBooksFromAuthorOderByReleaseDate(String firstName, String lastName) {
        bookService.findAllBooksByAuthorOrderByReleaseDate(firstName,lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsOrderedByCountOfTheirBooks() {
        authorService.getAllAuthorsOrderedByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        bookService.findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfter2000(int year) {
        bookService.findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBook();
    }
}
