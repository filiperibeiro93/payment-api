package com.flexpag.desafio2.models.dtos;

import com.flexpag.desafio2.models.PaymentSchedule;
import com.flexpag.desafio2.models.enums.PaymentStatus;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

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

    public static Page<PaymentScheduleDto> converter(Page<PaymentSchedule> list) {
        List<PaymentScheduleDto> listDto = list.stream().map(PaymentScheduleDto::new).toList();
        return new PageImpl<>(listDto);
    }

    public static PaymentScheduleDto converter(PaymentSchedule paymentSchedule) {
        return new PaymentScheduleDto(paymentSchedule);
    }
}
