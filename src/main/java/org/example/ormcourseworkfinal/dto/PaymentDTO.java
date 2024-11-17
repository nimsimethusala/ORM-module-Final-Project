package org.example.ormcourseworkfinal.dto;

import lombok.*;
import org.example.ormcourseworkfinal.entity.Registration;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDTO {
    private String paymentId;
    private double amount;
    private double balance;
    private Date date;
    private String status;
    private Registration registration;
}
