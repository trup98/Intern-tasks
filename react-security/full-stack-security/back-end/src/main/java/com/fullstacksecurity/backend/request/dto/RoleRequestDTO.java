package com.fullstacksecurity.backend.request.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleRequestDTO {


  @NotEmpty(message = "Role Cannot Be Empty")
  @Size(min = 6, max = 15)
  private String roleName;

}
