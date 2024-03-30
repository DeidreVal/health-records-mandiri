package com.mandiri.healthrecords.service;

import com.mandiri.healthrecords.model.entity.Doctor;
import com.mandiri.healthrecords.model.entity.Drug;

import java.util.List;

public interface DrugService {
    Drug saveDrug(Drug drug);
    List<Drug> getDrugById(String id);
    List<Drug> getAllDrug();
    Drug updateDrug(Drug drug);
    void deleteDrug(String id);
}
