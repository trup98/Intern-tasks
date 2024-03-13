package com.springboot.transactionmanagement.service;

import com.springboot.transactionmanagement.dto.FlightBookingRequest;

public interface FightBookingService {

    void bookTicket(FlightBookingRequest bookingRequest);
}
