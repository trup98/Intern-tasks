package feignClient.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientEntity {

    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
}
