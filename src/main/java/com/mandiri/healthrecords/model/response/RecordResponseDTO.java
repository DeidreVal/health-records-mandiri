package com.mandiri.healthrecords.model.response;

import com.mandiri.healthrecords.model.entity.Doctor;
import com.mandiri.healthrecords.model.entity.Drug;
import com.mandiri.healthrecords.model.entity.Patient;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordResponseDTO {
    private String id;
    private Patient patient;
    private LocalDateTime visitDate;
    private String diagnosis;
    private Drug drug;
    private Doctor doctor;
}
