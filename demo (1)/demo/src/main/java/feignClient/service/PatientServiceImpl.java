package feignClient.service;

import feignClient.external.FeignClientService;
import feignClient.model.ModelApiResponse;
import feignClient.model.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final FeignClientService feignClientService;

    @Override
    public ResponseEntity<ModelApiResponse> getAllPatients() {
        ResponseEntity<ModelApiResponse> allPatients = this.feignClientService.getAllPatients();
        System.out.println("Body::::::::::::::::::::::::::::::::::::::::::::::::::"+allPatients.getBody().getData());
        return allPatients;
    }

    @Override
    public ResponseEntity<ModelApiResponse> addPatient(RegisterRequestDTO registerRequestDTO) {
        ResponseEntity<ModelApiResponse> modelApiResponseResponseEntity = this.feignClientService.registerPatient(registerRequestDTO);
        log.info("Patient Added :::::::::::::::::::::::::::::::::::::"+modelApiResponseResponseEntity);
        return modelApiResponseResponseEntity;
    }

    @Override
    public ResponseEntity<ModelApiResponse> findPatientById(Long id) {
        ResponseEntity<ModelApiResponse> patientById = this.feignClientService.getPatientById(id);
        log.info("Patient Found By ID:::::::::::::::::::::::::::::::::::::::::::::::::::::::"+patientById.getBody());
        return patientById;
    }

    @Override
    public ResponseEntity<ModelApiResponse> updatePatient(Long id, RegisterRequestDTO registerRequestDTO) {
        ResponseEntity<ModelApiResponse> modelApiResponseResponseEntity = this.feignClientService.updatePatient(id, registerRequestDTO);
        log.info("Update patient Successfully::::::::::::::::::::::::::::::::::"+modelApiResponseResponseEntity.getBody());
        return modelApiResponseResponseEntity;
    }

    @Override
    public ResponseEntity<ModelApiResponse> deletePatient(Long id) {
        ResponseEntity<ModelApiResponse> deletePatient = this.feignClientService.deletePatient(id);
        log.info("Patient Deleted successfully:::::::::::::::::::::::::::::::::"+deletePatient.getBody());
        return deletePatient;
    }
}
