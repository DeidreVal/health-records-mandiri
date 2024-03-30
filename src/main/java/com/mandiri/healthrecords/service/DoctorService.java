package com.mandiri.healthrecords.service;

import com.mandiri.healthrecords.model.entity.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    List<Doctor> getDoctorById(String id);
    List<Doctor> getAllDoctor();
    Doctor updateDoctor(Doctor doctor);
    void deleteDoctor(String id);
}
