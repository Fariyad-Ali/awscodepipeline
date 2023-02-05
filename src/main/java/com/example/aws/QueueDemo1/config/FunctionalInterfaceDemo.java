package com.example.aws.QueueDemo1.config;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceDemo {

    public static void main(String[] args) {

/*        Consumer<String> consumer = (T)->{
            System.out.println(T);
        };

        consumer.accept("check consumer");
        List<String> names = Arrays.asList("Fariyad","Ali");

        names.forEach(consumer);*/


/*        Predicate<Integer> predicate = t -> t % 2 == 0;
        System.out.println(predicate.test(3));
        List<Integer> numbers = Arrays.asList(1,3,4,6,8);
        numbers.stream().filter(predicate).forEach(number -> {System.out.println(number);});*/

        Supplier<String> supplier = ()-> "this is dummy supplier";

        List<String> quotes = Arrays.asList();
        quotes.stream().findAny().orElseGet(supplier);


    }
}
