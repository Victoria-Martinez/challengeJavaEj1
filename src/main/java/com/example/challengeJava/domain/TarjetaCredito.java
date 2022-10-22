package com.example.challengeJava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
@Data
@AllArgsConstructor
public class TarjetaCredito {
    Marca marca;
    String numero;
    CardHolder cardHolder;
    LocalDate vencimiento;
}




