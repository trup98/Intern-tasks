package feignClient.service;

import com.vue.backend.dto.PatientResponseDTO;
import com.vue.backend.dto.RegisterRequestDTO;
import com.vue.backend.exception.CustomException;
import com.vue.backend.model.PatientEntity;
import com.vue.backend.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    public void registerPatient(RegisterRequestDTO requestDTO) {
        try {
            var patient = this.patientRepository.findByEmail(requestDTO.getEmail());
            if (patient.isPresent()) {
                throw new CustomException("Email already exists", HttpStatus.BAD_REQUEST);
            }

            PatientEntity patientEntity = modelMapper.map(requestDTO, PatientEntity.class);
            patientRepository.save(patientEntity);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }

    @Override
    public List<PatientResponseDTO> getAllPatients() {
        try {
            return this.patientRepository.findAll().stream()
                    .map(this::mapToPatientResponseDTO)
                    .toList();
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public PatientResponseDTO mapToPatientResponseDTO(PatientEntity patientEntity) {
        return modelMapper.map(patientEntity, PatientResponseDTO.class);
    }

    @Override
    public PatientResponseDTO getPatientById(Long id) {
        try {
            var patient = this.patientRepository.findById(id);
            if (patient.isEmpty()) {
                throw new CustomException("Patient not found", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(patient.get(), PatientResponseDTO.class);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }

    @Override
    public void updatePatient(Long pId, RegisterRequestDTO requestDTO) {
        try {
            var patient = this.patientRepository.findById(pId);
            if (patient.isEmpty()) {
                throw new CustomException("Patient not found", HttpStatus.NOT_FOUND);
            }
            PatientEntity patientEntity = patient.get();
            patientEntity.setName(requestDTO.getName());
            patientEntity.setEmail(requestDTO.getEmail());
            patientEntity.setPhoneNumber(requestDTO.getPhoneNumber());
            patientEntity.setGender(requestDTO.getGender());
            patientRepository.save(patientEntity);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }

    @Override
    public void deletePatient(Long id) {
        try {
            var patient = this.patientRepository.findById(id);
            if (patient.isEmpty()) {
                throw new CustomException("Patient not found", HttpStatus.NOT_FOUND);
            }
            this.patientRepository.deleteById(id);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }



}
