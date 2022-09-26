package com.flexpag.desafio2.repositories;

import com.flexpag.desafio2.models.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, Long> {
}
