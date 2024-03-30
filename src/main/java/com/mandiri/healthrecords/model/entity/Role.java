package com.mandiri.healthrecords.model.entity;

import com.mandiri.healthrecords.constant.ERole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mst_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private ERole role;
}
