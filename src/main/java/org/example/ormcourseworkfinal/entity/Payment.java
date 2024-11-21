package org.example.ormcourseworkfinal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

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
    private double balance;
    private Date date;
    private String status;

    @ManyToOne
    private Registration registration;

    /*public Payment(String paymentId, double amount, double balance, Date date, String status) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.balance = balance;
        this.date = date;
        this.status = status;

    }*/
}
