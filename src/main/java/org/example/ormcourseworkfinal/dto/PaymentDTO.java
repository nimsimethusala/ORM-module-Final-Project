package org.example.ormcourseworkfinal.dto;

import lombok.*;
import org.example.ormcourseworkfinal.entity.Registration;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDTO {
    private String paymentId;
    private double amount;
    private String status;
    private Registration registration;
}
