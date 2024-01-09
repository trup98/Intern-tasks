package com.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class EmployeeVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "employee_name")
    private String employeeName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id",referencedColumnName = "project_id")
    private List<ProjectVo> projectVos;

}

