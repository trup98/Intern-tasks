package com.learning.springbatch3.config;

import com.learning.springbatch3.entity.CustomerEntity;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<CustomerEntity,CustomerEntity> {
    @Override
    public CustomerEntity process(CustomerEntity item) throws Exception {
        return item;
    }
}
