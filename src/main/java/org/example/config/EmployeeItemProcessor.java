package org.example.config;

import org.example.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Date;

public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeItemProcessor.class);

    @Override
    public Employee process(final Employee employee) throws Exception {
        String firstName = employee.getFirstName().toUpperCase();
        String lastName = employee.getLastName().toUpperCase();
        String email = employee.getEmail().toUpperCase();
        Date birthDate = employee.getBirthdate();

        Employee transformedEmployee = new Employee(firstName, lastName, email,birthDate);
        LOGGER.info("Converting ( {} ) into ( {} )", employee, transformedEmployee);

        return transformedEmployee;
    }


}
