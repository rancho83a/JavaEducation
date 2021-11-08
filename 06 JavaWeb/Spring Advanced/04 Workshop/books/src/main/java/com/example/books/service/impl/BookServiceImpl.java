package com.example.books.service.impl;

import com.example.books.model.dto.AuthorDTO;
import com.example.books.model.dto.BookDTO;
import com.example.books.model.entity.AuthorEntity;
import com.example.books.model.entity.BookEntity;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;


    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(Long id) {
        return this.bookRepository.findById(id).map(this::map);
    }

    @Override
    public void deleteBookById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public long createBook(BookDTO bookDTO) {

        AuthorEntity author = this.authorRepository.findByName(bookDTO.getAuthor().getName())
                .orElseGet(()->
                    new AuthorEntity().setName(bookDTO.getAuthor().getName()
                ));
        this.authorRepository.save(author);

        BookEntity book = new BookEntity()
                .setAuthor(author)
                .setIsbn(bookDTO.getIsbn())
                .setTitle(bookDTO.getTitle());
        BookEntity save = this.bookRepository.save(book);
        return         save.getId();
    }

    @Override
    public Long updateBook(BookDTO bookDTO) {


        BookEntity bookEntity = bookRepository.findById(bookDTO.getId())
                .orElse(null);
        if (bookEntity == null) {
            return null;
        }

        AuthorEntity author = authorRepository.
                findByName(bookDTO.getAuthor().getName()).
                orElseGet(() -> new AuthorEntity().setName(bookDTO.getAuthor().getName()));
        this.authorRepository.save(author);


        bookEntity.setTitle(bookDTO.getTitle())
                .setIsbn(bookDTO.getIsbn())
                .setAuthor(author);
        return bookRepository.save(bookEntity).getId();
    }


    private BookDTO map(BookEntity book) {
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        AuthorDTO author = modelMapper.map(book.getAuthor(), AuthorDTO.class);

        bookDTO.setAuthor(author);

        return bookDTO;
    }
}
