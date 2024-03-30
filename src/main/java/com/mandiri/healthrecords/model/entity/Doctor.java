package com.mandiri.healthrecords.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mst_doctor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    @Id
    @Column(name = "doctor_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String department;
    private String phoneNumber;
    private Double rate;
}
