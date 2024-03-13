package com.springboot.transactionmanagement.controller;

import com.springboot.transactionmanagement.dto.ApiResponse;
import com.springboot.transactionmanagement.dto.FlightBookingRequest;
import com.springboot.transactionmanagement.service.FightBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/airline")
@RequiredArgsConstructor
public class FlightBookingController {

    private final FightBookingService fightBookingService;

    @PostMapping("/book")
    public ResponseEntity<ApiResponse> bookFight(@RequestBody FlightBookingRequest bookingRequest) {
        this.fightBookingService.bookTicket(bookingRequest);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Flight Book Successfully", new HashMap<>()), HttpStatus.OK);
    }
}
