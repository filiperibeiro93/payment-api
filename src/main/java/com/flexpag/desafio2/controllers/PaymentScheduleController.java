package com.flexpag.desafio2.controllers;

import com.flexpag.desafio2.models.PaymentSchedule;
import com.flexpag.desafio2.services.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/payments")
public class PaymentScheduleController {

    @Autowired
    private PaymentScheduleService service;

    @GetMapping
    public List<PaymentSchedule> list () {
        return service.findAll();
    }
}
