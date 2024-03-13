package feignClient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
}
