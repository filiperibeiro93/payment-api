package com.flexpag.desafio2.repositories;

import com.flexpag.desafio2.models.PaymentSchedule;
import com.flexpag.desafio2.models.enums.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, Long> {
    Page<PaymentSchedule> findByPaymentStatus(PaymentStatus status, Pageable pagination);
}
