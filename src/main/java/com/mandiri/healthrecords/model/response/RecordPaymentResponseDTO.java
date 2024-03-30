package com.mandiri.healthrecords.model.response;

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
    private String diagnosis;
    private List<DrugDetailResponseDTO> drugDetail;
    private String doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private Double doctorRate;
    private Double TotalPayment;
}
