package com.learning.springbatch3.config;

import com.learning.springbatch3.entity.CustomerEntity;
import com.learning.springbatch3.partitioner.CustomerRowPartitioner;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final CustomerWriter customerWriter;

    //    reader
    @Bean
    public FlatFileItemReader<CustomerEntity> fileItemReader() {
        FlatFileItemReader<CustomerEntity> reader = new FlatFileItemReader<>();
        reader.setName("csv-customer");
        reader.setResource(new FileSystemResource("src/main/resources/customers.xlsx"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        return reader;
    }


    private LineMapper<CustomerEntity> lineMapper() {
        DefaultLineMapper<CustomerEntity> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setStrict(false);
//        define column names;
        delimitedLineTokenizer.setNames("id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob");

//        define mapping class;
        BeanWrapperFieldSetMapper<CustomerEntity> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CustomerEntity.class);

        lineMapper.setFieldSetMapper(fieldSetMapper);
        lineMapper.setLineTokenizer(delimitedLineTokenizer);
        return lineMapper;
    }

    //    processor
    @Bean
    public CustomerProcessor processor() {
        return new CustomerProcessor();
    }

    //    partitioner
    @Bean
    public CustomerRowPartitioner partitioner() {
        return new CustomerRowPartitioner();
    }

    @Bean
    public Step childStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("step1", jobRepository).<CustomerEntity, CustomerEntity>chunk(1000, platformTransactionManager).reader(fileItemReader()).processor(processor()).writer(customerWriter).taskExecutor(taskExecutor()).build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(30);
        taskExecutor.setCorePoolSize(30);
        taskExecutor.setQueueCapacity(1000);
        return taskExecutor;
    }

    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("dumping-csv", jobRepository).flow(childStep(jobRepository, platformTransactionManager)).end().build();
    }

    @Bean
    public TaskExecutorPartitionHandler partitionHandler(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        TaskExecutorPartitionHandler partitionHandler = new TaskExecutorPartitionHandler();
//        define the thread
        partitionHandler.setGridSize(10);
        partitionHandler.setStep(childStep(jobRepository, platformTransactionManager));
        partitionHandler.setTaskExecutor(taskExecutor());
        return partitionHandler;
    }

    @Bean
    public Step parentStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("parentStep", jobRepository).partitioner("childStep", partitioner()).partitionHandler(partitionHandler(jobRepository, platformTransactionManager)).build();
    }
}
