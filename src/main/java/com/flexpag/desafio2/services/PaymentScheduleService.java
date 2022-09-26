package com.flexpag.desafio2.services;

import com.flexpag.desafio2.models.PaymentSchedule;
import com.flexpag.desafio2.models.dtos.PaymentScheduleDto;
import com.flexpag.desafio2.models.forms.PaymentScheduleForm;
import com.flexpag.desafio2.repositories.PaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentScheduleService {

    @Autowired
    private PaymentScheduleRepository repository;

    public List<PaymentScheduleDto> findAll() {
        return PaymentScheduleDto.converter(repository.findAll());
    }

    public ResponseEntity<PaymentScheduleDto> findById(Long id) {
        Optional<PaymentSchedule> optionalPayment = repository.findById(id);
        return optionalPayment.map(paymentSchedule -> ResponseEntity.ok().body(PaymentScheduleDto.converter(paymentSchedule)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public PaymentSchedule save(PaymentScheduleForm form) {
        return repository.save(PaymentScheduleForm.converter(form));
    }

    public ResponseEntity<?> delete(Long id) {
        Optional<PaymentSchedule> optionalPayment = repository.findById(id);
        if (optionalPayment.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
