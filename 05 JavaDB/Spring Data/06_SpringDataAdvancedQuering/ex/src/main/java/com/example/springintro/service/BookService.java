package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllBooksTitleWithAgeRestriction(AgeRestriction ageRestriction);

    List<String> getAllGoldenbook();

    List<String> findAllByPriceLessThanAndPriceGreaterThan();

    List<String> getNotReleasedBook(int year);
    List<String> getAllByTitleContains(String title);

    List<String> getAllByAuthor_LastNameStartsWith(String pattern);



    List<String> getBooksReleasedBeforeDate(LocalDate date);

    int getNumberOfBooksWithTitleLongerThan(int num);

    List<String> getAllByTitle(String title);

    int updateCopiesByReleaseDate(int copies, LocalDate date);

    int     removeAllBooksByCopiesLessThan(int num);

    void changePriceBook(Long bookId);

    int totalBookByAuthor(String fName, String lName);




}