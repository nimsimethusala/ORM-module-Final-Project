package org.example.ormcourseworkfinal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Registration registration;
}
