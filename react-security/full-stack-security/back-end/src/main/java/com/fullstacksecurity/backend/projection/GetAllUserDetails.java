package com.fullstacksecurity.backend.projection;

import java.util.Date;

public interface GetAllUserDetails {

  Long getId();

  String getFirstName();

  String getLastName();

  String getUserName();

  String getGender();

  String getEmail();

  String getRoleNames();

  Date getDateOfBirth();

  boolean getStatus();
}
