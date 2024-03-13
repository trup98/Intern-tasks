package com.springbatch.config;

import com.springbatch.entity.CustomerEntity;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<CustomerEntity, CustomerEntity> {

    @Override
    public CustomerEntity process(CustomerEntity item) {
        return item;
/*        if (item.getCountry().equals("United States")) {
            return item;
        } else {
            return null;
        }*/
    }
}