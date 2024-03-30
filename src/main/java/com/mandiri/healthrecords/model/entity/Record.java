package com.mandiri.healthrecords.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record {
    @Id
    @Column(name = "record_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime visitDate;
    private String diagnosis;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
