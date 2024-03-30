package com.mandiri.healthrecords.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mandiri.healthrecords.constant.ESex;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "mst_patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {
    @Id
    @Column(name = "patient_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private ESex sex;
    private String address;
    private String phoneNumber;
}
