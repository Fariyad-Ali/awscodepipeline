package com.example.aws.QueueDemo1.lamda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FunctionalInterfaces {

@Autowired
BookDao bookRepo;

    @Bean
    Function<String,List<Book>> reverse(){

        return (t)-> bookRepo.buildBooks().stream().filter(book-> book.getName().equals("JAVA")).collect(Collectors.toList());
    }

    @Bean
    Supplier<List<Book>> getBooks()
    {
       return () -> bookRepo.buildBooks();
    }

}
