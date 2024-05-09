package com.learning.fullstack.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_master")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@DynamicInsert
public class UserEntity extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "user_name")
  private String userName;
  @Column(name = "first_ name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "email")
  private String email;
  @Column(name = "gender")
  private String gender;
  @Column(name = "address")
  private String address;

  @Column(name = "dob")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dob;


}
