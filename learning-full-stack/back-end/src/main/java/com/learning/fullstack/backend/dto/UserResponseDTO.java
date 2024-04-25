package com.learning.fullstack.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
  private Long id;
  private String userName;
  private String firstName;
  private String lastName;
  private String email;
  private String gender;
  private LocalDate dob;
  private List<HobbyResponseDTO> hobbyResponseDTOS;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class HobbyResponseDTO {
    private String hobbyNames;
  }
}
