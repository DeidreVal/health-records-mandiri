package com.mandiri.healthrecords.controller;

import com.mandiri.healthrecords.constant.ApiPathConstant;
import com.mandiri.healthrecords.model.entity.Record;
import com.mandiri.healthrecords.model.request.RecordRequestInsertDTO;
import com.mandiri.healthrecords.model.request.RecordRequestUpdateDTO;
import com.mandiri.healthrecords.model.response.RecordPaymentResponseDTO;
import com.mandiri.healthrecords.model.response.RecordResponseDTO;
import com.mandiri.healthrecords.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstant.API + ApiPathConstant.RECORD)
public class RecordController {
    private final RecordService recordService;

    @PostMapping
    public List<RecordResponseDTO> saveRecord(@RequestBody RecordRequestInsertDTO recordRequestInsertDTO) {
        return recordService.saveRecord(recordRequestInsertDTO);
    }

    @GetMapping("/all")
    public List<Record> getAllRecord() {
        return recordService.getAllRecord();
    }

    @GetMapping("/{id}")
    public List<Record> getRecordById(@PathVariable String id) {
        return recordService.getRecordById(id);
    }

    @PutMapping
    public RecordResponseDTO updateRecord(@RequestBody RecordRequestUpdateDTO recordRequestUpdateDTO) {
        return recordService.updateRecord(recordRequestUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable String id) {
        recordService.deleteRecord(id);
    }

    @GetMapping("/payment/{patientId}/{date}/{doctorId}")
    public RecordPaymentResponseDTO paymentRecord(@PathVariable String patientId, @PathVariable LocalDateTime date, @PathVariable String doctorId) {
        return recordService.paymentRecord(patientId, date, doctorId);
    }
}
