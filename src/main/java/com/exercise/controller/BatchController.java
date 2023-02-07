package com.exercise.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired private Job job;

    @GetMapping("/runBatchJob")
    public BatchStatus runBatchJob() {
        JobParameters jobParameters = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
        try {
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
            return jobExecution.getStatus();
        } catch (Exception e) {
            return BatchStatus.FAILED;
        }
    }
}
