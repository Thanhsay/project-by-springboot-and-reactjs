package com.example.ndt.promanagement.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_gender")
    private int gender;

    @Column(name = "employee_position")
    private int position;

    @Column(name = "employee_state")
    private int state;

    @Column(name = "employee_age")
    private int age;

    @Column(name = "employee_username")
    private String username;

    @Column(name = "employee_password")
    private String password;

//    @ManyToMany(mappedBy = "employeeCollection", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Collection<Project> projectCollection;

//    @OneToMany(mappedBy = "employeeDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Details> employeeListDetails;

}
