package com.mandiri.healthrecords.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mst_drug")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Drug {
    @Id
    @Column(name = "drug_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String drugName;
    private String description;
    private Double price;
}
