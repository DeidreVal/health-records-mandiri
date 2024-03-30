package com.mandiri.healthrecords.controller;

import com.mandiri.healthrecords.constant.ApiPathConstant;
import com.mandiri.healthrecords.model.entity.Doctor;
import com.mandiri.healthrecords.model.entity.Drug;
import com.mandiri.healthrecords.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstant.API + ApiPathConstant.DRUG)
public class DrugController {
    private final DrugService drugService;

    @PostMapping
    public Drug saveDrug(@RequestBody Drug drug) {
        return drugService.saveDrug(drug);
    }

    @GetMapping("/all")
    public List<Drug> getAllDrug() {
        return drugService.getAllDrug();
    }

    @GetMapping("/{id}")
    public List<Drug> getDrugById(@PathVariable String id) {
        return drugService.getDrugById(id);
    }

    @PutMapping
    public Drug updateDrug(@RequestBody Drug drug) {
        return drugService.updateDrug(drug);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable String id) {
        drugService.deleteDrug(id);
    }
}
