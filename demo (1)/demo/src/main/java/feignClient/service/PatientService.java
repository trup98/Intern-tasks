package feignClient.service;

import com.vue.backend.dto.PatientResponseDTO;
import com.vue.backend.dto.RegisterRequestDTO;

import java.util.List;

public interface PatientService {
    void registerPatient(RegisterRequestDTO requestDTO);

    List<PatientResponseDTO> getAllPatients();

    PatientResponseDTO getPatientById(Long id);

    void updatePatient(Long id, RegisterRequestDTO requestDTO);

    void deletePatient(Long id);


}
