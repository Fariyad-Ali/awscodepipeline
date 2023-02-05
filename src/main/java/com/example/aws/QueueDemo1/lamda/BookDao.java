package com.example.aws.QueueDemo1.lamda;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class BookDao {
    public List<Book> buildBooks()
    {
        return Stream.of(new Book(1,"Java"),new Book(2,"AWS"),new Book(3,"JAVA")).collect(Collectors.toList());
    }
}
