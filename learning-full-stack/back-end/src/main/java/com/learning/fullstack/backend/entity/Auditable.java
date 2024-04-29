package com.learning.fullstack.backend.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
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

  @Column(name = "is_active", nullable = false)
  @ColumnDefault("true")
  private boolean isActive = Boolean.TRUE;

  @Column(name = "is_deleted", nullable = false)
  @ColumnDefault("false")
  private boolean isDeleted = Boolean.FALSE;

}
