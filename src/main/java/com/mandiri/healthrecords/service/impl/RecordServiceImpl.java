package com.mandiri.healthrecords.service.impl;

import com.mandiri.healthrecords.model.entity.Doctor;
import com.mandiri.healthrecords.model.entity.Drug;
import com.mandiri.healthrecords.model.entity.Patient;
import com.mandiri.healthrecords.model.entity.Record;
import com.mandiri.healthrecords.model.request.RecordRequestInsertDTO;
import com.mandiri.healthrecords.model.request.RecordRequestUpdateDTO;
import com.mandiri.healthrecords.model.response.DrugDetailResponseDTO;
import com.mandiri.healthrecords.model.response.RecordPaymentResponseDTO;
import com.mandiri.healthrecords.model.response.RecordResponseDTO;
import com.mandiri.healthrecords.repository.RecordRepository;
import com.mandiri.healthrecords.service.RecordService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    @PersistenceContext
    private final EntityManager entityManager;
    private final RecordRepository recordRepository;

    @Transactional
    @Override
    public List<RecordResponseDTO> saveRecord(RecordRequestInsertDTO recordRequestInsertDTO) {
        List<RecordResponseDTO> recordResponseDTOList = new ArrayList<>();
        for (String drugId : recordRequestInsertDTO.getDrugId()) {
            String id = String.valueOf(UUID.randomUUID());

            String query = "INSERT INTO patient_record(record_id, patient_id, visit_date, diagnosis, drug_id, doctor_id) VALUES (?, ?, ?, ?, ?, ?)";
            entityManager.createNativeQuery(query)
                    .setParameter(1, id)
                    .setParameter(2, recordRequestInsertDTO.getPatientId())
                    .setParameter(3, recordRequestInsertDTO.getVisitDate())
                    .setParameter(4, recordRequestInsertDTO.getDiagnosis())
                    .setParameter(5, drugId)
                    .setParameter(6, recordRequestInsertDTO.getDoctorId())
                    .executeUpdate();

            Patient patient = recordRepository.findById(id).get().getPatient();
            Drug drug = recordRepository.findById(id).get().getDrug();
            Doctor doctor = recordRepository.findById(id).get().getDoctor();

            RecordResponseDTO recordResponseDTO = new RecordResponseDTO();
            recordResponseDTO.setId(id);
            recordResponseDTO.setPatient(patient);
            recordResponseDTO.setVisitDate(recordRequestInsertDTO.getVisitDate());
            recordResponseDTO.setDiagnosis(recordRequestInsertDTO.getDiagnosis());
            recordResponseDTO.setDrug(drug);
            recordResponseDTO.setDoctor(doctor);
            recordResponseDTOList.add(recordResponseDTO);
        }

        return recordResponseDTOList;
    }

    @Override
    public List<Record> getRecordById(String id) {
        String query = "SELECT * FROM patient_record WHERE record_id = :id";

        List<Record> record = entityManager.createNativeQuery(query, Record.class)
                .setParameter("id", id)
                .getResultList();
        return record;
    }

    @Override
    public List<Record> getAllRecord() {
        String query = "SELECT * FROM patient_record";

        List<Record> record = entityManager.createNativeQuery(query, Record.class)
                .getResultList();
        return record;
    }

    @Transactional
    @Override
    public RecordResponseDTO updateRecord(RecordRequestUpdateDTO recordRequestUpdateDTO) {
        String query = "UPDATE patient_record SET patient_id = ?, visit_date = ?, diagnosis = ?, drug_id = ?, doctor_id = ? WHERE record_id = ?";
        entityManager.createNativeQuery(query)
                .setParameter(1, recordRequestUpdateDTO.getPatientId())
                .setParameter(2, recordRequestUpdateDTO.getVisitDate())
                .setParameter(3, recordRequestUpdateDTO.getDiagnosis())
                .setParameter(4, recordRequestUpdateDTO.getDrugId())
                .setParameter(5, recordRequestUpdateDTO.getDoctorId())
                .setParameter(6, recordRequestUpdateDTO.getId())
                .executeUpdate();

        Patient patient = recordRepository.findById(recordRequestUpdateDTO.getId()).get().getPatient();
        Drug drug = recordRepository.findById(recordRequestUpdateDTO.getId()).get().getDrug();
        Doctor doctor = recordRepository.findById(recordRequestUpdateDTO.getId()).get().getDoctor();

        RecordResponseDTO recordResponseDTO = new RecordResponseDTO();
        recordResponseDTO.setId(recordRequestUpdateDTO.getId());
        recordResponseDTO.setPatient(patient);
        recordResponseDTO.setVisitDate(recordRequestUpdateDTO.getVisitDate());
        recordResponseDTO.setDiagnosis(recordRequestUpdateDTO.getDiagnosis());
        recordResponseDTO.setDrug(drug);
        recordResponseDTO.setDoctor(doctor);

        return recordResponseDTO;
    }

    @Transactional
    @Override
    public void deleteRecord(String id) {
        String query = "DELETE FROM patient_record WHERE record_id = :id";
        entityManager.createNativeQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public RecordPaymentResponseDTO paymentRecord(String patientId, LocalDateTime date, String doctorId) {
//        String query = "SELECT ARRAY_AGG(record_id) AS record_id, visit_date, ARRAY_AGG(diagnosis) as diagnosis, ARRAY_AGG(doctor_id) AS doctor_id, ARRAY_AGG(drug_id) AS drug_id, patient_id FROM patient_record WHERE patient_id = :id GROUP BY patient_id, visit_date";
        String query = "SELECT * FROM patient_record WHERE patient_id = :id AND visit_date = :date AND doctor_id = :doctorId";

        List<Record> record = entityManager.createNativeQuery(query, Record.class)
                .setParameter("id", patientId)
                .setParameter("date", date)
                .setParameter("doctorId", doctorId)
                .getResultList();

        Patient patient = record.get(0).getPatient();
        Doctor doctor = record.get(0).getDoctor();
        Double total = 0.0;
        total += record.get(0).getDoctor().getRate();

        RecordPaymentResponseDTO recordPaymentResponseDTO = new RecordPaymentResponseDTO();
        List<DrugDetailResponseDTO> drugDetailResponseDTOList = new ArrayList<>();
        for (Record rec : record) {
            Drug drug = recordRepository.findById(rec.getId()).get().getDrug();
            DrugDetailResponseDTO drugDetailResponseDTO = new DrugDetailResponseDTO();
            total += drug.getPrice();

            drugDetailResponseDTO.setDrugId(drug.getId());
            drugDetailResponseDTO.setDrugName(drug.getDrugName());
            drugDetailResponseDTO.setDrugPrice(drug.getPrice());
            drugDetailResponseDTOList.add(drugDetailResponseDTO);
        }

        recordPaymentResponseDTO.setPatientId(patient.getId());
        recordPaymentResponseDTO.setFirstName(patient.getFirstName());
        recordPaymentResponseDTO.setLastName(patient.getLastName());
        recordPaymentResponseDTO.setVisitDate(record.get(0).getVisitDate());
        recordPaymentResponseDTO.setDiagnosis(record.get(0).getDiagnosis());
        recordPaymentResponseDTO.setDrug(drugDetailResponseDTOList);
        recordPaymentResponseDTO.setDoctorId(doctor.getId());
        recordPaymentResponseDTO.setDoctorFirstName(doctor.getFirstName());
        recordPaymentResponseDTO.setDoctorLastName(doctor.getLastName());
        recordPaymentResponseDTO.setDoctorRate(doctor.getRate());
        recordPaymentResponseDTO.setTotalPayment(total);

        return recordPaymentResponseDTO;
    }
}
