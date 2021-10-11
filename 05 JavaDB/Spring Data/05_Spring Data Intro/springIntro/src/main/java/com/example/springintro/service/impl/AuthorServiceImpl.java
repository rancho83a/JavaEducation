package com.example.springintro.service.impl;

import com.example.springintro.model.entity.Author;
import com.example.springintro.repository.AuthorRepository;
import com.example.springintro.service.AuthorService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    public static final String AUTHOR_FILE_PATH="src/main/resources/files/authors.txt";
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {

        if(authorRepository.count()>0){
            return;
        }

        Files.readAllLines(Path.of(AUTHOR_FILE_PATH))
                .stream()
                .filter(row-> !row.isBlank())
                .forEach(a->{
                    String[] tokens = a.split("\\s+");

                    Author author =new Author(tokens[0], tokens[1]);

                    this.authorRepository.save(author);
                });


    }

    @Override
    public Author getRandomAuthor() {

        long randomId = ThreadLocalRandom
                .current().nextLong(1, authorRepository.count()+1);


        return authorRepository.findById(randomId).orElse(null);
    }

    @Override
    public List<String> getAllAuthorsOrderedByCountOfTheirBooks() {
        return authorRepository.findAllByBooksSizeDesc()
                .stream()
                .map(a->String.format("%s %s %d", a.getFirstName(), a.getLastName(), a.getBooks().size()))
                .collect(Collectors.toList());
    }
}
