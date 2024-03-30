package com.mandiri.healthrecords.model.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrugDetailResponseDTO {
    private String drugId;
    private String drugName;
    private Double drugPrice;
}
