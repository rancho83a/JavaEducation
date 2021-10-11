package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal price, BigDecimal price2);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate lower, LocalDate upper);

    List<Book> findAllByTitleContains(String title);

    List<Book> findAllByAuthor_LastNameStartsWith(String pattern);

    @Query("SELECT count (b.id) FROM Book b WHERE LENGTH( b.title)> :num")
        //int countBooksByTitleLengthGreatThan(int num);
    int countBooksByTitleLengthGreatThan(@Param(value = "num") int num);

    List<Book> findAllByTitle(String title);

    @Modifying
    @Query("update Book b SET b.copies=b.copies+ :num where b.releaseDate> :date")
    int updateCopiesAfterReleaseDate(int num, LocalDate date);

    @Modifying
    int removeAllByCopiesLessThan(int num);

    @Procedure("change_book_price_by_Id")
    void changeBookPriceById(Long id);

    @Procedure("total_book_by_author")
    int totalBookByAuthor(String fName, String lName);


}
