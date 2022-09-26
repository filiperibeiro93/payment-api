package com.flexpag.desafio2.controllers;

import com.flexpag.desafio2.models.PaymentSchedule;
import com.flexpag.desafio2.models.dtos.PaymentScheduleDto;
import com.flexpag.desafio2.models.forms.PaymentScheduleForm;
import com.flexpag.desafio2.services.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping
    @Transactional
    public ResponseEntity<PaymentScheduleDto> save(@RequestBody @Valid PaymentScheduleForm form,
                                                   UriComponentsBuilder uriBuilder) {
        return service.save(form, uriBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PaymentScheduleDto> update(@PathVariable Long id,
                                                     @RequestBody @Valid PaymentScheduleForm form) {
        return service.update(id, form);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
