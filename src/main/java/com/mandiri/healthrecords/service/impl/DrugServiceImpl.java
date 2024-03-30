package com.mandiri.healthrecords.service.impl;

import com.mandiri.healthrecords.model.entity.Doctor;
import com.mandiri.healthrecords.model.entity.Drug;
import com.mandiri.healthrecords.service.DrugService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DrugServiceImpl implements DrugService {
    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    @Override
    public Drug saveDrug(Drug drug) {
        String id = String.valueOf(UUID.randomUUID());

        String query = "INSERT INTO mst_drug(drug_id, drug_name, description, price) VALUES (?, ?, ?, ?)";
        entityManager.createNativeQuery(query)
                .setParameter(1, id)
                .setParameter(2, drug.getDrugName())
                .setParameter(3, drug.getDescription())
                .setParameter(4, drug.getPrice())
                .executeUpdate();

        return Drug.builder()
                .id(id)
                .drugName(drug.getDrugName())
                .description(drug.getDescription())
                .price(drug.getPrice())
                .build();
    }

    @Override
    public List<Drug> getDrugById(String id) {
        String query = "SELECT * FROM mst_drug WHERE drug_id = :id";

        List<Drug> drug = entityManager.createNativeQuery(query, Drug.class)
                .setParameter("id", id)
                .getResultList();
        return drug;
    }

    @Override
    public List<Drug> getAllDrug() {
        String query = "SELECT * FROM mst_drug";

        List<Drug> drug = entityManager.createNativeQuery(query, Drug.class)
                .getResultList();
        return drug;
    }

    @Transactional
    @Override
    public Drug updateDrug(Drug drug) {
        String query = "UPDATE mst_drug SET drug_name = ?, description = ?, price = ? WHERE drug_id = ?";
        entityManager.createNativeQuery(query)
                .setParameter(1, drug.getDrugName())
                .setParameter(2, drug.getDescription())
                .setParameter(3, drug.getPrice())
                .setParameter(4, drug.getId())
                .executeUpdate();

        return Drug.builder()
                .id(drug.getId())
                .drugName(drug.getDrugName())
                .description(drug.getDescription())
                .price(drug.getPrice())
                .build();
    }

    @Transactional
    @Override
    public void deleteDrug(String id) {
        String query = "DELETE FROM mst_drug WHERE drug_id = :id";
        entityManager.createNativeQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }
}
