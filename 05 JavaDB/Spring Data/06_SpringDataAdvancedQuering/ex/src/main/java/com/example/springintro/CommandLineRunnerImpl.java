package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        System.out.println("Please select number of task: ");
        int ex = Integer.parseInt(bufferedReader.readLine());

        switch (ex) {
            case 1 -> booksTitleByAgeRestriction();
            case 2 -> goldenBook();
            case 3 -> booksWithPriceLessThan4AndGreatThan40();
            case 4 -> notReleasedBook();
            case 5 -> booksReleaseBefore();
            case 6 -> authorsSearch();
            case 7 -> booksSearch();
            case 8 -> bookTitlesSearch();
            case 9 -> countBooks();
            case 10 -> totalBookCopies();
            case 11 -> reducedBook();
            case 12 -> increaseBookCopies();
            case 13 -> removeBooks();
            case 14 -> totalAmountBookProcedure();
            case 15 -> changeBookPriceByIdProcedure();


        }
        //printAllBooksAfterYear(2000);
        //printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        //   printAllAuthorsAndNumberOfTheirBooks();
        //pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

    }

    private void totalAmountBookProcedure() throws IOException {

/*
CREATE PROCEDURE FIRST:

create
    definer = root@localhost procedure total_book_by_author(IN f_name varchar(50), IN l_name varchar(50))
begin
    Select Count(b.id)
    from authors a
    JOIN books b on a.id = b.author_id
    WHERE a.first_name = f_name
    AND a.last_name = l_name
    GROUP BY a.id;
end;

 */

        String fullName=bufferedReader.readLine();
        String[] name = fullName.split("\\s+");
        int amount = this.bookService.totalBookByAuthor(name[0], name[1]);
        System.out.println(String.format("%s has written %d books",fullName, amount));

    }

    private void changeBookPriceByIdProcedure() {
/*
Create Procedure First:

CREATE PROCEDURE change_book_price_by_Id(book_id BIGINT)
begin
    UPDATE books b
    SET b.price =b.price+100
    WHERE b.id=book_id;

end;
 */
        this.bookService.changePriceBook(1L);
    }

    private void removeBooks() throws IOException {
        System.out.println("Enter books`s copies:");
        int num = Integer.parseInt(bufferedReader.readLine());
        int deletedBooks = this.bookService.removeAllBooksByCopiesLessThan(num);

        System.out.println("Deleted Books " + deletedBooks);
    }


    private void increaseBookCopies() throws IOException {
        System.out.println("Enter date in the format dd MMM yyyy");
        String input = bufferedReader.readLine();
        LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        System.out.println("Enter encrease number of copies ");
        int num = Integer.parseInt(bufferedReader.readLine());
        int total = this.bookService.updateCopiesByReleaseDate(num, date);

        System.out.println(total * num);
        System.out.println(String.format
                ("COMMENT:%n %s books are released after %s, so total of %s book copies were added",
                        total, date, total * num));
    }

    private void reducedBook() throws IOException {
        System.out.println("Enter title:");
        this.bookService.getAllByTitle(bufferedReader.readLine())
                .forEach(System.out::println);

    }

    private void totalBookCopies() {
        this.authorService.getTotalBookCopiesByAuthor().forEach(System.out::println);
    }

    private void countBooks() throws IOException {
        System.out.println("Enter the length of title:");
        int num = Integer.parseInt(bufferedReader.readLine());
        int count = this.bookService.getNumberOfBooksWithTitleLongerThan(num);

        System.out.println(String.format("There are %d books with longer title than %d symbols", count, num));


    }

    private void bookTitlesSearch() throws IOException {
        System.out.println("Enter pattern (for authors last name):");
        this.bookService.getAllByAuthor_LastNameStartsWith(bufferedReader.readLine().toLowerCase())
                .forEach(System.out::println);
    }

    private void booksSearch() throws IOException {
        System.out.println("Enter pattern:");
        this.bookService.getAllByTitleContains(bufferedReader.readLine().toLowerCase()).forEach(System.out::println);
    }

    private void authorsSearch() throws IOException {
        System.out.println("Enter pattern:");
        this.authorService.getAllByFirstNameEndingWith(bufferedReader.readLine())
                .forEach(System.out::println);

    }

    private void booksReleaseBefore() throws IOException {
        System.out.println("Enter release date (the date will be in the format dd-MM-yyyy):");
        LocalDate date = LocalDate.parse(bufferedReader.readLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.bookService.getBooksReleasedBeforeDate(date).forEach(System.out::println);
    }

    private void notReleasedBook() throws IOException {
        System.out.println("Enter year of release:");
        int year = Integer.parseInt(bufferedReader.readLine());
        this.bookService.getNotReleasedBook(year).forEach(System.out::println);
    }

    private void booksWithPriceLessThan4AndGreatThan40() {
        this.bookService.findAllByPriceLessThanAndPriceGreaterThan().forEach(System.out::println);
    }

    private void goldenBook() {
        this.bookService.getAllGoldenbook().forEach(System.out::println);
    }

    private void booksTitleByAgeRestriction() throws IOException {
        System.out.println("Enter Age Restriction (MINOR, TEEN, ADULT  are allowed)");
        AgeRestriction ageRestriction = AgeRestriction.valueOf(bufferedReader.readLine().toUpperCase());
        this.bookService.findAllBooksTitleWithAgeRestriction(ageRestriction).forEach(System.out::println);
    }


    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
