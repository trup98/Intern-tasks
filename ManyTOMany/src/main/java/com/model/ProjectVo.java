package com.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class ProjectVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id",referencedColumnName = "employee_id")
    private List<EmployeeVo> employeeVoList;
}
