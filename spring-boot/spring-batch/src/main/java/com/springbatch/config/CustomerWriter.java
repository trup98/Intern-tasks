package com.springbatch.config;

import com.springbatch.entity.CustomerEntity;
import com.springbatch.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerWriter implements ItemWriter<CustomerEntity> {

    private final CustomerRepository customerRepository;

    @Override
    public void write(List<? extends CustomerEntity> list) throws Exception {
        System.out.println("Thread Name::::::" + Thread.currentThread().getName());
        customerRepository.saveAll(list);

    }
}
