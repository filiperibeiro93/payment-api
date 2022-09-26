package com.flexpag.desafio2.services;

import com.flexpag.desafio2.models.PaymentSchedule;
import com.flexpag.desafio2.models.dtos.PaymentScheduleDto;
import com.flexpag.desafio2.models.forms.PaymentScheduleForm;
import com.flexpag.desafio2.repositories.PaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    public ResponseEntity<PaymentScheduleDto> save(PaymentScheduleForm form,
                                                   UriComponentsBuilder uriBuilder) {
        PaymentSchedule payment = repository.save(PaymentScheduleForm.converter(form));
        URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.created(uri).body(new PaymentScheduleDto(payment));
    }

    public ResponseEntity<PaymentScheduleDto> update(Long id, PaymentScheduleForm form) {
        Optional<PaymentSchedule> optionalPayment = repository.findById(id);
        if (optionalPayment.isPresent()) {
            PaymentSchedule paymentSchedule = optionalPayment.get();
            paymentSchedule.setPaymentDate(form.getPaymentDate());
            paymentSchedule.setPayment(form.getPayment());
            paymentSchedule.setDescription(form.getDescription());
            return ResponseEntity.ok().body(PaymentScheduleDto.converter(paymentSchedule));
        }
        return ResponseEntity.notFound().build();
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
