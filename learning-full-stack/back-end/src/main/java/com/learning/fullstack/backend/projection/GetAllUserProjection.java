package com.learning.fullstack.backend.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface GetAllUserProjection {
  Long getId();

  String getFirstName();

  String getLastName();

  String getUserName();

  String getGender();

  String getEmail();

  String getHobbyNames();

  Date getDob();

  boolean getStatus();

}
