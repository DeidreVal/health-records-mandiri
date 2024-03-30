package com.mandiri.healthrecords.controller;

import com.mandiri.healthrecords.constant.ApiPathConstant;
import com.mandiri.healthrecords.model.entity.Doctor;
import com.mandiri.healthrecords.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstant.API + ApiPathConstant.DOCTOR)
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    public Doctor saveDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    @GetMapping("/all")
    public List<Doctor> getAllDoctor() {
        return doctorService.getAllDoctor();
    }

    @GetMapping("/{id}")
    public List<Doctor> getDoctorById(@PathVariable String id) {
        return doctorService.getDoctorById(id);
    }

    @PutMapping
    public Doctor updateDoctor(@RequestBody Doctor doctor) {
        return doctorService.updateDoctor(doctor);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable String id) {
        doctorService.deleteDoctor(id);
    }
}
