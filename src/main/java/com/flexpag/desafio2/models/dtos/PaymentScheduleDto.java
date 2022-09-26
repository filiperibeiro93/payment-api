package com.flexpag.desafio2.models.dtos;

import com.flexpag.desafio2.models.PaymentSchedule;
import com.flexpag.desafio2.models.enums.PaymentStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PaymentScheduleDto {

    private Long id;
    private LocalDateTime paymentDate;
    private PaymentStatus paymentStatus;

    public PaymentScheduleDto(PaymentSchedule paymentSchedule) {
        this.id = paymentSchedule.getId();
        this.paymentDate = paymentSchedule.getPaymentDate();
        this.paymentStatus = paymentSchedule.getPaymentStatus();
    }

    public static List<PaymentScheduleDto> converter(List<PaymentSchedule> list) {
        return list.stream().map(PaymentScheduleDto::new).toList();
    }
}
