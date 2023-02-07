package com.exercise.service;

import com.exercise.model.Employee;
import com.exercise.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("Job finished successfully!");
            List<Employee> employeeList = employeeRepository.findAll();
            employeeList.forEach(employee -> System.out.println(employee.getFirstName() + " "+ employee.getEmail()));
        }
    }
}
