package com.example.ndt.promanagement.Controller;

import com.example.ndt.promanagement.Model.Details;
import com.example.ndt.promanagement.Model.Employee;
import com.example.ndt.promanagement.Model.Project;
import com.example.ndt.promanagement.Service.DetailsService;
import com.example.ndt.promanagement.Service.EmployeeService;
import com.example.ndt.promanagement.Service.ProjectService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/list/")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    ProjectService projectService;
    @Autowired
    DetailsService detailsService;

    @PutMapping("/emp/{id}")
    public List<Employee> findAllInProject(@PathVariable Long id){
        List<Employee> employeeList = employeeService.findAllEmployee();

        List<Employee> empList = new ArrayList<>();
        List<Details> detailsList = new ArrayList<>();
        detailsList = detailsService.findAllByProjectId(id);
        for(Object o : detailsList){
            Employee employee = new Employee();
            Details details = (Details) o;
            employee = employeeService.findEmployeeById(details.getDetailEmployeeId());
            empList.add(employee);
        }
        for(int i=0; i<employeeList.size(); i++){
            for(int j = 0; j<empList.size(); j++){
                if(empList.get(j) == employeeList.get(i)){
                    employeeList.remove(employeeList.get(i));
                }
            }
        }
        return employeeList;
    }

    @GetMapping("/employee")
    public List<Employee> findAll(){
        return employeeService.findAllEmployee();
    }

    @Data
    public class PaginationListEmp{
        int activePage;
        List<Employee> employees;
        int totalPage;
    }

    //pagination onloading page = 1
    @GetMapping("/employee/{page}")
    public PaginationListEmp paginationEmployee(@PathVariable int page){
        PaginationListEmp paginationListEmp = new PaginationListEmp();
        int offset = 5;
        int totalItems = employeeService.getAmountEmployees();
        int totalPages;
        if(totalItems%offset==0){
            totalPages = totalItems/offset;
        }else{
            totalPages= totalItems/offset + 1;
        }
        List<Employee> employeeList = employeeService.paginationEmployee(page);
        paginationListEmp.setActivePage(1);
        paginationListEmp.setTotalPage(totalPages);
        paginationListEmp.setEmployees(employeeList);
        return paginationListEmp;
    }

    //pagination
    @PutMapping("/employee/{page}")
    public PaginationListEmp paginationEmployeePut (@PathVariable int page){
        PaginationListEmp paginationListEmp = new PaginationListEmp();
        int offset = 5;
        int totalItems = employeeService.getAmountEmployees();
        int totalPages;
        if(totalItems%offset==0){
            totalPages = totalItems/offset;
        }else{
            totalPages= totalItems/offset + 1;
        }
        List<Employee> employeeList = employeeService.paginationEmployee(page);
        paginationListEmp.setActivePage(page);
        paginationListEmp.setTotalPage(totalPages);
        paginationListEmp.setEmployees(employeeList);
        return paginationListEmp;
    }

    //find employee by Id
    @GetMapping("/empUpdate/{id}")
    public Employee getEmpbyId(@PathVariable Long id){
        Employee employee = employeeService.findEmployeeById(id);
        return employee;
    }

    //update
    @PutMapping("/empUpdate/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        Employee employee1 = employeeService.findEmployeeById(id);
        employee1.setEmployeeName(employee.getEmployeeName());
        employee1.setGender(employee.getGender());
        employee1.setAge(employee.getAge());
        employee1.setPosition(employee.getPosition());
        employee1.setState(employee.getState());
        Employee updateEmployee = employeeService.saveEmployee(employee1);
        return updateEmployee;
    }

    //save
    @PostMapping("/empUpdate")
    public Employee addNewEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    //delete by Id
    @DeleteMapping("/empDelete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        String state;
        if(employeeService.deleteEmp(id)){
            state = "success";
        }else{
            state = "fail";
        }
        return state;
    }

    //find all projects that employee has joined
    @PutMapping("/empDetail/{id}")
    public List<Project> findProjectsByEmployeeId(@PathVariable Long id){
        List<Details> detailsList = employeeService.getAllProjectsOfEmployee(id);
        List<Project> projectList = new ArrayList<>();
        for(Object o : detailsList){
            Details details = new Details();
            details =  (Details) o;
            Project project = new Project();
            try{
                if(projectService.findProjectById(details.getDetailProjectId()) != null){
                    project =  projectService.findProjectById(details.getDetailProjectId());
                    projectList.add(project);
                }
            }finally {
                continue;
            }
        }
        return projectList;
    }

    //searching
    @PutMapping("/searchEmp/{log}")
    public List<Employee> searchingEmployee(@PathVariable String log){
        return employeeService.searchEmployee(log);
    }
}
