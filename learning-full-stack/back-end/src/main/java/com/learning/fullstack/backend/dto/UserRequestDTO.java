package com.learning.fullstack.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
  private String userName;
  private String firstName;
  private String lastName;
  private String email;
  private String gender;
  private String address;
  private LocalDate dob;
  private List<Integer> hobbyIdList;
}
