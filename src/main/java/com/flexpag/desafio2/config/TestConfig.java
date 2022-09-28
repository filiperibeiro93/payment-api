package com.flexpag.desafio2.config;

import com.flexpag.desafio2.models.PaymentSchedule;
import com.flexpag.desafio2.models.User;
import com.flexpag.desafio2.repositories.PaymentScheduleRepository;
import com.flexpag.desafio2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private PaymentScheduleRepository paymentScheduleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        PaymentSchedule p1 = new PaymentSchedule(LocalDateTime.parse("2022-07-30 11:00", formatter), 500.0,
                "referente à neoenergia PE");
        PaymentSchedule p2 = new PaymentSchedule(LocalDateTime.parse("2022-10-10 08:00", formatter), 700.0,
                "referente à compesa");
        PaymentSchedule p3 = new PaymentSchedule(LocalDateTime.parse("2022-09-28 10:00", formatter), 435.0,
                "referente ao detran");
        PaymentSchedule p4 = new PaymentSchedule(LocalDateTime.parse("2022-05-28 07:00", formatter), 600.5,
                "qualquer texto");
        PaymentSchedule p5 = new PaymentSchedule(LocalDateTime.parse("2022-04-28 10:30", formatter), 350.0,
                "");
        PaymentSchedule p6 = new PaymentSchedule(LocalDateTime.parse("2022-11-28 10:00", formatter), 812.0,
                "celpe");
        PaymentSchedule p7 = new PaymentSchedule(LocalDateTime.parse("2022-12-28 10:00", formatter), 122.0,
                "teste");
        PaymentSchedule p8 = new PaymentSchedule(LocalDateTime.parse("2022-03-28 10:00", formatter), 573.0,
                "aqui um texto pra preencher espaço");
        PaymentSchedule p9 = new PaymentSchedule(LocalDateTime.parse("2022-03-28 10:00", formatter), 573.0,
                "aqui um texto pra preencher espaço");
        PaymentSchedule p10 = new PaymentSchedule(LocalDateTime.parse("2022-03-28 10:00", formatter), 573.0,
                "aqui um texto pra preencher espaço");

        paymentScheduleRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

        User u1 = new User("Filipe", "filipe@email.com", "$2a$10$PyPuiUdyNKzjHgyl75gNtO2s/HFqWf300h9bKgvOQySR4aroqybc2");

        userRepository.save(u1);
    }
}
