package com.blogs;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Math.E;


public class MainUtils {
    public static void main(String[] args) {
        List<Employee> data = Arrays.asList(new Employee("mike",5000), new Employee("stallin",10000),new Employee("adam",5000));
        Map<Double, List<Employee>> groups = data.stream().collect(Collectors.groupingBy(Employee::getSalary));
        System.out.println(groups);

        for(Map.Entry<Double, List<Employee>> entry : groups.entrySet()){
            double salary = entry.getKey();
            List<Employee> employeeList = entry.getValue();
            System.out.println("Employee with salary " +salary+ ":");
            for (Employee employee:
                employeeList ) {
                System.out.println(employee.getName());
            }
        }
    }
}