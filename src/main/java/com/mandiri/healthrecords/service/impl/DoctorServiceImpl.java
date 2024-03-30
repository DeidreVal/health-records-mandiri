package com.mandiri.healthrecords.service.impl;

import com.mandiri.healthrecords.model.entity.Doctor;
import com.mandiri.healthrecords.service.DoctorService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    @Override
    public Doctor saveDoctor(Doctor doctor) {
        String id = String.valueOf(UUID.randomUUID());

        String query = "INSERT INTO mst_doctor(doctor_id, first_name, last_name, department, phone_number, rate) VALUES (?, ?, ?, ?, ?, ?)";
        entityManager.createNativeQuery(query)
                .setParameter(1, id)
                .setParameter(2, doctor.getFirstName())
                .setParameter(3, doctor.getLastName())
                .setParameter(4, doctor.getDepartment())
                .setParameter(5, doctor.getPhoneNumber())
                .setParameter(6, doctor.getRate())
                .executeUpdate();

        return Doctor.builder()
                .id(id)
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .department(doctor.getDepartment())
                .phoneNumber(doctor.getPhoneNumber())
                .rate(doctor.getRate())
                .build();
    }

    @Override
    public List<Doctor> getDoctorById(String id) {
        String query = "SELECT * FROM mst_doctor WHERE doctor_id = :id";

        List<Doctor> doctor = entityManager.createNativeQuery(query, Doctor.class)
                .setParameter("id", id)
                .getResultList();
        return doctor;
    }

    @Override
    public List<Doctor> getAllDoctor() {
        String query = "SELECT * FROM mst_doctor";

        List<Doctor> doctor = entityManager.createNativeQuery(query, Doctor.class)
                .getResultList();
        return doctor;
    }

    @Transactional
    @Override
    public Doctor updateDoctor(Doctor doctor) {
        String query = "UPDATE mst_doctor SET first_name = ?, last_name = ?, department = ?, phone_number = ?, rate = ? WHERE doctor_id = ?";

        entityManager.createNativeQuery(query)
                .setParameter(1, doctor.getFirstName())
                .setParameter(2, doctor.getLastName())
                .setParameter(3, doctor.getDepartment())
                .setParameter(4, doctor.getPhoneNumber())
                .setParameter(5, doctor.getRate())
                .setParameter(6, doctor.getId())
                .executeUpdate();

        return Doctor.builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .department(doctor.getDepartment())
                .phoneNumber(doctor.getPhoneNumber())
                .rate(doctor.getRate())
                .build();
    }

    @Transactional
    @Override
    public void deleteDoctor(String id) {
        String query = "DELETE FROM mst_doctor WHERE doctor_id = :id";
        entityManager.createNativeQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }
}
