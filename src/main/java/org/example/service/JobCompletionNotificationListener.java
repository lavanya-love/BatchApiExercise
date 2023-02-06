package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("Job finished successfully!");
            List<Employee> employeeList = employeeRepository.findAll();
            employeeList.forEach(employee -> System.out.println(employee.getFirstName() + " "+ employee.getEmail()));
        }
    }
}
