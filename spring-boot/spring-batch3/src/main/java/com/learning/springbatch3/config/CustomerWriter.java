package com.learning.springbatch3.config;

import com.learning.springbatch3.entity.CustomerEntity;
import com.learning.springbatch3.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerWriter implements ItemWriter<CustomerEntity> {
    private final CustomerRepository customerRepository;

    @Override
    public void write(Chunk<? extends CustomerEntity> chunk) throws Exception {
        System.out.println("Thread Name ::::::::::" + Thread.currentThread().getName());
        customerRepository.saveAll(chunk);
    }
}
