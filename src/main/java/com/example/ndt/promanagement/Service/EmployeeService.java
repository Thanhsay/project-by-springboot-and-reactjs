package com.example.ndt.promanagement.Service;

import com.example.ndt.promanagement.Model.Details;
import com.example.ndt.promanagement.Model.Employee;
import com.example.ndt.promanagement.Model.Project;
import com.example.ndt.promanagement.Repo.DetailsRepo;
import com.example.ndt.promanagement.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    DetailsRepo detailsRepo;

    //find by ID
    public Employee findEmployeeById(Long id){
        return employeeRepo.findById(id).get();
    }
    //find All
    public List<Employee> findAllEmployee(){
        return (List<Employee>) employeeRepo.findAll();
    }

    //pagination
    public List<Employee> paginationEmployee(int page){
        int offset = 5;
        Pageable pageable = PageRequest.of(page-1, offset, Sort.by("employeeName").ascending());
        Page<Employee> employeePage = employeeRepo.findAll(pageable);
        List<Employee> employeeList = employeePage.getContent();
        return  employeeList;
    }

    //get amount employees
    public int getAmountEmployees(){
        return (int) employeeRepo.count();
    }

    //save
    public Employee saveEmployee(Employee employee){
       return employeeRepo.save(employee);
    }

    //delete by id
    public Boolean deleteEmp(Long id){
        employeeRepo.deleteById(id);
        return true;
    }
    //find details by employee id
    public List<Details> getAllProjectsOfEmployee(Long id){
        return detailsRepo.findByEmployeeId(id);
    }
    //searching
    public List<Employee> searchEmployee(String log){
        return employeeRepo.searchEmp("%"+log+"%");
    }
}
