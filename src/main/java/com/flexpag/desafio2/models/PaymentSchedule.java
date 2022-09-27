package com.flexpag.desafio2.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.desafio2.models.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
public class PaymentSchedule implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone="GMT-3")
    private LocalDateTime paymentDate;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;
    private Double paymentValue;
    private String description;

    public PaymentSchedule() {
    }

    public PaymentSchedule(LocalDateTime paymentDate, Double paymentValue, String description) {
        this.paymentDate = paymentDate;
        this.paymentValue = paymentValue;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentSchedule that = (PaymentSchedule) o;
        return id.equals(that.id) && paymentDate.equals(that.paymentDate) && paymentValue.equals(that.paymentValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentDate, paymentValue);
    }
}
