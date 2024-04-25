package com.learning.fullstack.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_hobby_mapping")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserHobbyMappingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity userId;

  @ManyToOne
  @JoinColumn(name = "hobby_id")
  private HobbyEntity hobbyId;
}
