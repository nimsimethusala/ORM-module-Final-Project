package org.example.ormcourseworkfinal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Payment {
    @Id
    private String paymentId;
    private double amount;
    private String status;

    @ManyToOne
    private Registration registration;
}
