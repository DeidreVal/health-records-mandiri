package com.mandiri.healthrecords.service.impl;

import com.mandiri.healthrecords.constant.ESex;
import com.mandiri.healthrecords.model.entity.Patient;
import com.mandiri.healthrecords.service.PatientService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    @Override
    public Patient savePatient(Patient patient) {
        String id = String.valueOf(UUID.randomUUID());
        ESex sex;
        if (patient.getSex().toString().equalsIgnoreCase(ESex.FEMALE.name())) {
            sex = ESex.FEMALE;
        } else if (patient.getSex().toString().equalsIgnoreCase(ESex.MALE.name())) {
            sex = ESex.MALE;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Sex type is not allowed!");
        }

        String query = "INSERT INTO mst_patient(patient_id, first_name, last_name, date_of_birth, sex, address, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        entityManager.getTransaction().begin();
        entityManager.createNativeQuery(query)
                .setParameter(1, id)
                .setParameter(2, patient.getFirstName())
                .setParameter(3, patient.getLastName())
                .setParameter(4, patient.getDateOfBirth())
                .setParameter(5, sex.toString())
                .setParameter(6, patient.getAddress())
                .setParameter(7, patient.getPhoneNumber())
                .executeUpdate();
//        entityManager.getTransaction().commit();
//        System.out.println("Data inserted with native query!");

        return Patient.builder()
                .id(id)
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .dateOfBirth(patient.getDateOfBirth())
                .sex(sex)
                .address(patient.getAddress())
                .phoneNumber(patient.getPhoneNumber())
                .build();
    }

    @Override
    public List<Patient> getPatientById(String id) {
        String query = "SELECT * FROM mst_patient WHERE patient_id = :id";

        List<Patient> patient = entityManager.createNativeQuery(query, Patient.class)
                .setParameter("id", id)
                .getResultList();
        return patient;
    }

    @Override
    public List<Patient> getAllPatient() {
        String query = "SELECT * FROM mst_patient";

        List<Patient> patient = entityManager.createNativeQuery(query, Patient.class)
                .getResultList();
        return patient;
    }

    @Transactional
    @Override
    public Patient updatePatient(Patient patient) {
        ESex sex;
        if (patient.getSex().toString().equalsIgnoreCase(ESex.FEMALE.name())) {
            sex = ESex.FEMALE;
        } else if (patient.getSex().toString().equalsIgnoreCase(ESex.MALE.name())) {
            sex = ESex.MALE;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Sex type is not allowed!");
        }

        String query = "UPDATE mst_patient SET first_name = ?, last_name = ?, date_of_birth = ?, sex = ?, address = ?, phone_number = ? WHERE patient_id = ?";
//        entityManager.getTransaction().begin();
        entityManager.createNativeQuery(query)
                .setParameter(1, patient.getFirstName())
                .setParameter(2, patient.getLastName())
                .setParameter(3, patient.getDateOfBirth())
                .setParameter(4, sex.toString())
                .setParameter(5, patient.getAddress())
                .setParameter(6, patient.getPhoneNumber())
                .setParameter(7, patient.getId())
                .executeUpdate();

        return Patient.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .dateOfBirth(patient.getDateOfBirth())
                .sex(sex)
                .address(patient.getAddress())
                .phoneNumber(patient.getPhoneNumber())
                .build();
    }

    @Transactional
    @Override
    public void deletePatient(String id) {
        String query = "DELETE FROM mst_patient WHERE patient_id = :id";
        entityManager.createNativeQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }
}
