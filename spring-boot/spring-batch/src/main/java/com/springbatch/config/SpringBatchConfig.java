package com.springbatch.config;

import com.springbatch.entity.CustomerEntity;
import com.springbatch.partition.ThreadPartitioner;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
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

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SpringBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CustomerWriter customerWriter;

    //  to read the information(data) from the source(customer.csv);
    @Bean
    public FlatFileItemReader<CustomerEntity> reader() {
        FlatFileItemReader<CustomerEntity> fileItemReader = new FlatFileItemReader<>();
        fileItemReader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
        fileItemReader.setName("csvReader");
//      the first line contain id,firstName...
        fileItemReader.setLinesToSkip(1);
        fileItemReader.setLineMapper(lineMapper());
        return fileItemReader;
    }

    //    Configures a LineMapper to map each line of the CSV file to a CustomerEntity object.
    private LineMapper<CustomerEntity> lineMapper() {
        DefaultLineMapper<CustomerEntity> lineMapper = new DefaultLineMapper<>();

//      extract the value from csv
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames("id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob");


//      map the extract value to the target class
        BeanWrapperFieldSetMapper<CustomerEntity> setMapper = new BeanWrapperFieldSetMapper<>();
        setMapper.setTargetType(CustomerEntity.class);

        lineMapper.setLineTokenizer(delimitedLineTokenizer);
        lineMapper.setFieldSetMapper(setMapper);

        return lineMapper;
    }

    @Bean
    public CustomerProcessor processor() {
        return new CustomerProcessor();
    }


    //    partitioning the input data.
    @Bean
    public ThreadPartitioner partitioner() {
        return new ThreadPartitioner();
    }

    @Bean
    public PartitionHandler partitionHandler() {
        TaskExecutorPartitionHandler taskExecutorPartitionHandler = new TaskExecutorPartitionHandler();
//       input data will be split into two partitions.
        taskExecutorPartitionHandler.setGridSize(2);
        taskExecutorPartitionHandler.setTaskExecutor(taskExecutor());
        taskExecutorPartitionHandler.setStep(salveStep());
        return taskExecutorPartitionHandler;
    }

    //    Uses the reader, processor, and writer defined earlier.
    @Bean
    public Step salveStep() {
        return stepBuilderFactory.get("salveStep").<CustomerEntity, CustomerEntity>chunk(500).reader(reader()).processor(processor()).writer(customerWriter).build();
    }

    @Bean
    public Step masterStep() {
        return stepBuilderFactory.get("masterStep").
                partitioner(salveStep().getName(), partitioner()).
                partitionHandler(partitionHandler()).
                build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("importCustomer").flow(masterStep()).end().build();
    }

    //    to handle multithreading
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(4);
        threadPoolTaskExecutor.setCorePoolSize(4);
        threadPoolTaskExecutor.setQueueCapacity(4);
        return threadPoolTaskExecutor;
    }

}
