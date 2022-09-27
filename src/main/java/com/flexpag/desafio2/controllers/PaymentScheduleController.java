package com.flexpag.desafio2.controllers;

import com.flexpag.desafio2.models.dtos.PaymentScheduleDto;
import com.flexpag.desafio2.models.enums.PaymentStatus;
import com.flexpag.desafio2.models.forms.PaymentForm;
import com.flexpag.desafio2.models.forms.PaymentScheduleForm;
import com.flexpag.desafio2.models.forms.UpdatePaymentForm;
import com.flexpag.desafio2.services.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/payments")
public class PaymentScheduleController {

    @Autowired
    private PaymentScheduleService service;

    @GetMapping
    @Cacheable(value = "paymentList")
    public Page<PaymentScheduleDto> list (@RequestParam(required = false) PaymentStatus paymentStatus,
                                          @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 5)
                                          Pageable pagination) {
        if (paymentStatus == null) {
            return service.findAll(pagination);
        }
        return service.findByPaymentStatus(paymentStatus, pagination);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentScheduleDto> findById (@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "paymentList", allEntries = true)
    public ResponseEntity<PaymentScheduleDto> save(@RequestBody @Valid PaymentScheduleForm form,
                                                   UriComponentsBuilder uriBuilder) {
        return service.save(form, uriBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "paymentList", allEntries = true)
    public ResponseEntity<PaymentScheduleDto> update(@PathVariable Long id,
                                                     @RequestBody @Valid UpdatePaymentForm form) {
        return service.update(id, form);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "paymentList", allEntries = true)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
