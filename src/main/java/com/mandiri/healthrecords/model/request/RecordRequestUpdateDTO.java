package com.mandiri.healthrecords.model.request;

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
public class RecordRequestUpdateDTO {
    private String id;
    private String patientId;
    private LocalDateTime visitDate;
    private String diagnosis;
    private String drugId;
    private String doctorId;
}
