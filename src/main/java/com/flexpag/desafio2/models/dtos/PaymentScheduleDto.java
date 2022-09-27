package com.flexpag.desafio2.models.dtos;

import com.flexpag.desafio2.models.PaymentSchedule;
import com.flexpag.desafio2.models.enums.PaymentStatus;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;


@Getter
public class PaymentScheduleDto {

    private Long id;
    private LocalDateTime paymentDate;
    private PaymentStatus status;

    public PaymentScheduleDto(PaymentSchedule paymentSchedule) {
        this.id = paymentSchedule.getId();
        this.paymentDate = paymentSchedule.getPaymentDate();
        this.status = paymentSchedule.getStatus();
    }

    public static Page<PaymentScheduleDto> converter(Page<PaymentSchedule> list) {
        return list.map(PaymentScheduleDto::new);
    }

    public static PaymentScheduleDto converter(PaymentSchedule paymentSchedule) {
        return new PaymentScheduleDto(paymentSchedule);
    }
}
