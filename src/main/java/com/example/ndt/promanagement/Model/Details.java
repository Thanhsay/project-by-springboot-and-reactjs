package com.example.ndt.promanagement.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "details")
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long details_id;

    @Column(name = "project_id")
    private Long detailProjectId;

    @Column(name = "employee_id")
    private Long detailEmployeeId;

    @Column(name = "description")
    private String description;

//    @ManyToOne
//    @JoinColumn(name = "project_id", nullable = false)
//    private Project projectDetail;
//
//    @ManyToOne
//    @JoinColumn(name = "employee_id", nullable = false)
//    private Employee employeeDetail;
}
