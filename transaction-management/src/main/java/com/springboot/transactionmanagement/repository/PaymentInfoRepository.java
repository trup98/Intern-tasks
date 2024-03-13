package com.springboot.transactionmanagement.repository;

import com.springboot.transactionmanagement.entity.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo,Long> {
}
