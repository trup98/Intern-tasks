package com.springboot.transactionmanagement.service.impl;

import com.springboot.transactionmanagement.dto.FlightBookingRequest;
import com.springboot.transactionmanagement.entity.PassengerInfo;
import com.springboot.transactionmanagement.entity.PaymentInfo;
import com.springboot.transactionmanagement.exception.CustomException;
import com.springboot.transactionmanagement.repository.PassengerInfoRepository;
import com.springboot.transactionmanagement.repository.PaymentInfoRepository;
import com.springboot.transactionmanagement.service.FightBookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FightBookingServiceImpl implements FightBookingService {

    private final PassengerInfoRepository passengerInfoRepository;
    private final PaymentInfoRepository paymentInfoRepository;


    @Override
    @Transactional
    public void bookTicket(FlightBookingRequest bookingRequest) {
       try {
           PassengerInfo passengerInfo = new PassengerInfo();
           passengerInfo.setPassengerName(bookingRequest.getPassengerName());
           passengerInfo.setTravelDate(bookingRequest.getTravelDate());
           passengerInfo.setDestination(bookingRequest.getDestination());
           passengerInfo.setEmail(bookingRequest.getEmail());
           passengerInfo.setSource(bookingRequest.getSource());

           Long passengerId = passengerInfoRepository.save(passengerInfo).getId();

           PaymentInfo paymentInfo = null;

           paymentInfo.setAccountNo(bookingRequest.getAccountNo());
           paymentInfo.setAmount(bookingRequest.getAmount());
           paymentInfo.setPassengerId(passengerId);

           paymentInfoRepository.save(paymentInfo);
       }catch (CustomException e){
           throw new CustomException(e.getMessage(),e.getHttpStatus());
       }
    }
}
