package com.flexpag.desafio2.services;

import com.flexpag.desafio2.models.PaymentSchedule;
import com.flexpag.desafio2.models.dtos.PaymentScheduleDto;
import com.flexpag.desafio2.models.enums.PaymentStatus;
import com.flexpag.desafio2.models.forms.PaymentScheduleForm;
import com.flexpag.desafio2.models.forms.UpdatePaymentForm;
import com.flexpag.desafio2.repositories.PaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentScheduleService {

    @Autowired
    private PaymentScheduleRepository repository;

    public Page<PaymentScheduleDto> findAll(Pageable pagination) {

        repository.findAll().forEach(p -> {
            if (!dateValid(p.getPaymentDate())) {
                p.setStatus(PaymentStatus.PAID);
                repository.save(p);
            }
        });

        return PaymentScheduleDto.converter(repository.findAll(pagination));
    }

    public Page<PaymentScheduleDto> findByStatus(PaymentStatus status, Pageable pagination) {

        repository.findAll().forEach(p -> {
            if (!dateValid(p.getPaymentDate())) {
                p.setStatus(PaymentStatus.PAID);
                repository.save(p);
            }
        });
        return PaymentScheduleDto.converter(repository.findByStatus(status, pagination));
    }

    public ResponseEntity<PaymentScheduleDto> findById(Long id) {
        Optional<PaymentSchedule> optionalPayment = repository.findById(id);
        if (!dateValid(optionalPayment.get().getPaymentDate())) {
            optionalPayment.get().setStatus(PaymentStatus.PAID);
            repository.save(optionalPayment.get());
        }
        return ResponseEntity.ok().body(PaymentScheduleDto.converter(optionalPayment.get()));
    }

    public ResponseEntity<PaymentScheduleDto> save(PaymentScheduleForm form,
                                                   UriComponentsBuilder uriBuilder) {
        PaymentSchedule payment = repository.save(PaymentScheduleForm.converter(form));
        URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.created(uri).body(new PaymentScheduleDto(payment));
    }

    public ResponseEntity<PaymentScheduleDto> update(Long id, UpdatePaymentForm form) {
        Optional<PaymentSchedule> optionalPayment = repository.findById(id);
        if (!dateValid(optionalPayment.get().getPaymentDate())) {
            optionalPayment.get().setStatus(PaymentStatus.PAID);
            repository.save(optionalPayment.get());
            return ResponseEntity.badRequest().build();
        } else if (form.getPaymentDate().isAfter(optionalPayment.get().getPaymentDate())) {
            return ResponseEntity.badRequest().build();
        }
        optionalPayment.get().setPaymentDate(form.getPaymentDate());
        return ResponseEntity.ok().body(PaymentScheduleDto.converter(optionalPayment.get()));
    }

    public ResponseEntity<?> delete(Long id) {
        Optional<PaymentSchedule> optionalPayment = repository.findById(id);
        if (dateValid(optionalPayment.get().getPaymentDate())) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    public static boolean dateValid(LocalDateTime date) {
        return date.isAfter(LocalDateTime.now());
    }

}
