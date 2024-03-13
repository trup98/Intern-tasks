package feignClient.service;

import feignClient.model.ModelApiResponse;
import feignClient.model.RegisterRequestDTO;
import org.springframework.http.ResponseEntity;

public interface PatientService {


    ResponseEntity<ModelApiResponse> getAllPatients();

    ResponseEntity<ModelApiResponse> addPatient(RegisterRequestDTO registerRequestDTO);

    ResponseEntity<ModelApiResponse> findPatientById(Long id);

    ResponseEntity<ModelApiResponse> updatePatient(Long id, RegisterRequestDTO registerRequestDTO);

    ResponseEntity<ModelApiResponse> deletePatient(Long id);
}
