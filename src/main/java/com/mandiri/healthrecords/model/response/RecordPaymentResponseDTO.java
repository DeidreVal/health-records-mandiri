package com.mandiri.healthrecords.model.response;

import com.mandiri.healthrecords.model.entity.Doctor;
import com.mandiri.healthrecords.model.entity.Drug;
import com.mandiri.healthrecords.model.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordPaymentResponseDTO {
    private String patientId;
    private String firstName;
    private String lastName;
    private LocalDateTime visitDate;
    private List<String> drugName;
    private String doctorFirstName;
    private String doctorLastName;
    private Doctor doctor;
}
