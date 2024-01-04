package com.vo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
public class CompanyVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String companyName;
    private String companyAddress;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "companyVo")
    @JoinTable(name = "employee_id")
    private List<EmployeeVo> employeeVos;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public List<EmployeeVo> getEmployeeVos() {
        return employeeVos;
    }

    public void setEmployeeVos(List<EmployeeVo> employeeVos) {
        this.employeeVos = employeeVos;
    }



}
