// infra/config/BatchJobConfig.java
package com.example.geradorarquivos.infra.config;

import com.example.geradorarquivos.application.tasklets.GerarArquivoTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final GerarArquivoTasklet tasklet;

    @Autowired
    public BatchJobConfig(JobRepository jobRepository,
                          PlatformTransactionManager transactionManager,
                          GerarArquivoTasklet tasklet) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.tasklet = tasklet;
    }

    @Bean
    public Job gerarArquivoJob() {
        return new JobBuilder("gerarArquivoJob", jobRepository)
                .start(gerarArquivoStep())
                .build();
    }

    @Bean
    public Step gerarArquivoStep() {
        return new StepBuilder("gerarArquivoStep", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }
}
