package com.flexpag.desafio2.controllers;

import com.flexpag.desafio2.models.dtos.PaymentScheduleDto;
import com.flexpag.desafio2.services.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/payments")
public class PaymentScheduleController {

    @Autowired
    private PaymentScheduleService service;

    @GetMapping
    public List<PaymentScheduleDto> list () {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentScheduleDto> findById (@PathVariable Long id) {
        return service.findById(id);
    }
}
