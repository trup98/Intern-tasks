package feignClient.controller;

import feignClient.model.ModelApiResponse;
import feignClient.model.RegisterRequestDTO;
import feignClient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/feign-client")
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/all")
    public ResponseEntity<ModelApiResponse> findAll() {
        return this.patientService.getAllPatients();
    }

    @PostMapping("/register/patient")
    public ResponseEntity<ModelApiResponse> addPatient(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return this.patientService.addPatient(registerRequestDTO);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ModelApiResponse> findById(@PathVariable(value = "id") Long id) {
        return this.patientService.findPatientById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ModelApiResponse> updatePatient(@PathVariable(value = "id") Long id, @RequestBody RegisterRequestDTO registerRequestDTO) {
        return this.patientService.updatePatient(id, registerRequestDTO);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ModelApiResponse> deletePatient(@PathVariable(value = "id") Long id) {
        return this.patientService.deletePatient(id);
    }

}
