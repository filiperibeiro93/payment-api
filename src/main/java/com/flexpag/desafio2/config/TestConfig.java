package com.flexpag.desafio2.config;

import com.flexpag.desafio2.models.PaymentSchedule;
import com.flexpag.desafio2.repositories.PaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private PaymentScheduleRepository repository;

    @Override
    public void run(String... args) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        PaymentSchedule p1 = new PaymentSchedule(LocalDateTime.parse("2022-07-30 11:00", formatter), 500.0,
                "referente à neoenergia PE");
        PaymentSchedule p2 = new PaymentSchedule(LocalDateTime.parse("2022-10-10 08:00", formatter), 700.0,
                "referente à compesa");
        PaymentSchedule p3 = new PaymentSchedule(LocalDateTime.parse("2022-09-28 10:00", formatter), 435.0,
                "referente ao detran");

        repository.saveAll(Arrays.asList(p1, p2, p3));
    }
}
