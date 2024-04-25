package com.learning.fullstack.backend.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class Auditable {

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_Date", nullable = false, updatable = false)
  private Date createDate;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_modified_date")
  private Date lastModifiedDate;

  //  this will define a column data type will be boolean and have a default value as a true
  @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
//  this will allow a null value in database;
//  @Basic(optional = true)
//  this can be used for setting a default value;
//  @ColumnDefault("true")
  private boolean isActive;

  @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
  private boolean isDeleted;

}
