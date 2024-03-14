package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data

public class EmployeeVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @Column(name = "employee_name")
    private String employeeName;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id",referencedColumnName = "company_id")
    private CompanyVo companyVo;
}
