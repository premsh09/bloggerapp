package com.blogapp58;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> data = Arrays.asList(
                new Person("mike", 21, "pune"),
                new Person("stalin", 26, "delhi"),
                new Person("adam", 24, "pune"),
                new Person("smith", 22, "delhi"),
                new Person("carl", 25, "pune")
        );

        Map<String, List<Person>> newData = data.stream().collect(Collectors.groupingBy(e -> e.getCity()));
        System.out.println(newData);

        newData.forEach((city, people)->{
            System.out.println("People in city:"+city +":"+people);
                }
        );
    }
}