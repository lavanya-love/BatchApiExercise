package com.exercise.controller;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BatchControllerTest {

    @Mock
    private JobLauncher jobLauncher;
    @Mock private Job job;

    @InjectMocks
    private BatchController batchController;

    @Test
    public void testRunBatchJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
        JobExecution jobExecution = new JobExecution(1L, jobParameters, "importEmployeeJob");
        jobExecution.setStatus(BatchStatus.COMPLETED);
        when(jobLauncher.run(job, jobParameters)).thenReturn(jobExecution);

        BatchStatus status = batchController.runBatchJob();

        verify(jobLauncher, times(1)).run(any(), any());
        assertEquals(BatchStatus.FAILED, status);
    }

}
