package com.mandiri.healthrecords.model.request;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordRequestInsertDTO {
    private String patientId;
    private LocalDateTime visitDate;
    private String diagnosis;
    private List<String> drugId;
    private String doctorId;
}
