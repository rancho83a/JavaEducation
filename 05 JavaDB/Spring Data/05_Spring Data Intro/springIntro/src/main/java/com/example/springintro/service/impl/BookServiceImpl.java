package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    public static final String BOOK_FILE_PATH = "src/main/resources/files/books.txt";
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBook() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(BOOK_FILE_PATH))
                .stream()
                .filter(b -> !b.isBlank())
                .forEach(b -> {
                    String[] tokens = b.split("\\s+");

                    Book book = createBookFromInfo(tokens);

                    bookRepository.save(book);
                });

    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository.findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository.findAllByReleaseDateBefore(LocalDate.of(year,1,1))
                .stream()
                .map(b->String.format("%s %s", b.getAuthor().getFirstName(), b.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorOrderByReleaseDate(String firstName, String lastName) {

        return bookRepository.findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName,lastName)
                .stream()
                .map(b-> String.format("%s %s %d", b.getTitle(), b.getReleaseDate(), b.getCopies()))
                .collect(Collectors.toList());
    }

    private Book createBookFromInfo(String[] info) {
        EditionType editionType = EditionType.values()[Integer.parseInt(info[0])];
        LocalDate releaseDate = LocalDate.parse(info[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(info[2]);
        BigDecimal price = new BigDecimal(info[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(info[4])];

        String title = Arrays.stream(info).skip(5).collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();

        Set<Category> categories = categoryService.getRandomCategory();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);
    }
}
