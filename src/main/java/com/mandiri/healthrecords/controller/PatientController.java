package com.mandiri.healthrecords.controller;

import com.mandiri.healthrecords.constant.ApiPathConstant;
import com.mandiri.healthrecords.model.entity.Patient;
import com.mandiri.healthrecords.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstant.API + ApiPathConstant.PATIENT)
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @GetMapping("/all")
    public List<Patient> getAllPatient() {
        return patientService.getAllPatient();
    }

    @GetMapping("/{id}")
    public List<Patient> getPatientById(@PathVariable String id) {
        return patientService.getPatientById(id);
    }

    @PutMapping
    public Patient updatePatient(@RequestBody Patient patient) {
        return patientService.updatePatient(patient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
    }
}
