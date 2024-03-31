package com.blogapp58;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<Employee> data = Arrays.asList(
                new Employee("mike", "HR", 85000),
                new Employee("adam", "IT", 60000),
                new Employee("stallin", "Developer", 90000),
                new Employee("smith", "Developer", 75000),
                new Employee("carl", "IT", 50000),
                new Employee("bob", "HR", 45000)
        );
        Map<String, Double> newData = data.stream().collect(Collectors.groupingBy(e -> e.getDepartment(), Collectors.averagingDouble(e -> e.getSalary())));
        System.out.println(newData);
    }
}
