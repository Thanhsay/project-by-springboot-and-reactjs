package com.example.ndt.promanagement.Repo;

import com.example.ndt.promanagement.Model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends PagingAndSortingRepository<Employee, Long> {
    @Query("SELECT u from Employee u WHERE u.employeeName like ?1")
    List<Employee> searchEmp(String log);
}
