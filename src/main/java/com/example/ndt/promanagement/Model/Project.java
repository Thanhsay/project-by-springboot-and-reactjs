package com.example.ndt.promanagement.Model;

import lombok.Data;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_start")
    private Date projectStart;

    @Column(name = "project_end")
    private Date projectEnd;

    @Column(name = "project_lead_name")
    private String leadName;

    @Column(name = "project_state")
    private int projectState;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "details",
//                joinColumns = @JoinColumn(name = "project_id", nullable = false),
//                inverseJoinColumns = @JoinColumn(name = "employee_id", nullable = false))
//    private Collection<Employee> employeeCollection;

//    @OneToMany(mappedBy = "projectDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Details> projectListDetail;
}
