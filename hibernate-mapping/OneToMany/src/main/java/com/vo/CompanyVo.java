package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
@Data
public class CompanyVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_address")
    private String companyAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyVo")
    private List<EmployeeVo> employeeVos;
}
