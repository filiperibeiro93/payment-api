package com.flexpag.desafio2.models.forms;

import com.flexpag.desafio2.models.PaymentSchedule;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class PaymentScheduleForm extends PaymentForm {


    @NotNull
    private Double payment;
    private String description;

    public static PaymentSchedule converter(PaymentScheduleForm form) {
        return new PaymentSchedule(form.getPaymentDate(), form.getPayment(), form.getDescription());
    }

}
