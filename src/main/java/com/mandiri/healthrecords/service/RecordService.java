package com.mandiri.healthrecords.service;

import com.mandiri.healthrecords.model.entity.Record;
import com.mandiri.healthrecords.model.request.RecordRequestInsertDTO;
import com.mandiri.healthrecords.model.request.RecordRequestUpdateDTO;
import com.mandiri.healthrecords.model.response.RecordPaymentResponseDTO;
import com.mandiri.healthrecords.model.response.RecordResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface RecordService {
    List<RecordResponseDTO> saveRecord(RecordRequestInsertDTO recordRequestInsertDTO);
    List<Record> getRecordById(String id);
    List<Record> getAllRecord();
    RecordResponseDTO updateRecord(RecordRequestUpdateDTO recordRequestUpdateDTO);
    void deleteRecord(String id);
    RecordPaymentResponseDTO paymentRecord(String patientId, LocalDateTime date, String doctorId);
}
