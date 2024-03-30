package com.mandiri.healthrecords.service;

import com.mandiri.healthrecords.constant.ESex;
import com.mandiri.healthrecords.model.entity.Patient;

import java.util.Date;
import java.util.List;

public interface PatientService {
    Patient savePatient(Patient patient);
    List<Patient> getPatientById(String id);
    List<Patient> getAllPatient();
    Patient updatePatient(Patient patient);
    void deletePatient(String id);
}
