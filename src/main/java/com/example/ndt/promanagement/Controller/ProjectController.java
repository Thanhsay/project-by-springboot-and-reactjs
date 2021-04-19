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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/project/")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DetailsService detailsService;

    @GetMapping("/list")
    public List<Project> home(){
       // model.addAttribute("projectFindAll", projectService.projectFindAll());
        return projectService.projectFindAll();
    }
    //add project
    @PostMapping("/list")
    public Project add(@RequestBody Project project){
        return projectService.addNewProject(project);
    }
    //add details
    @PostMapping("/list/employee")
    public Details addDetails(@RequestBody Details details){
        return detailsService.detailsSave(details);
    }
    //Delete details
    @DeleteMapping("/list/employee/{project_id}/{employee_id}")
    public String deleteDetails(@PathVariable Long project_id, @PathVariable Long employee_id){
        String stateDelete = "";
        List<Details> detailsList =  detailsService.findDetails(project_id, employee_id);
        for(Object o : detailsList){
            Details details = (Details) o;
            if(detailsService.deleteDetails(details)){
                stateDelete = "success";
            }else
                stateDelete = "fail";
            }
        return stateDelete;
    }

    @GetMapping("/list/{id}")
    public Project findById(@PathVariable Long id){
        return projectService.findProjectById(id);
    }

    @PutMapping("/list/{id}")
    public Project edit(@PathVariable Long id,
                        @RequestBody Project projectDetail){
        Project project = projectService.findProjectById(id);
        project.setProjectName(projectDetail.getProjectName());
        project.setProjectStart(projectDetail.getProjectStart());
        project.setProjectEnd(projectDetail.getProjectEnd());
        project.setLeadName(projectDetail.getLeadName());
        project.setProjectState(projectDetail.getProjectState());
        Project updateProject = projectService.addNewProject(project);
        return updateProject;
    }
    @DeleteMapping("/list/{id}")
    public String delete(@PathVariable Long id){
        String state;
       if( projectService.deleteProject(id)){
           state = "success";
       }else
           state = "fail";
       return state;
    }

//    @GetMapping("list/pagination/{page}")
//    public List<Project> paginationList(@RequestParam(defaultValue = "1") int page){
//        int totalItems = projectService.allItems();
//        List<Project> projects = (List<Project>) projectService.pagination(page);
//        return projects;
//    }




    @Data
    public class PaginationList{
        int activePage;
        List<Project> projects;
        int totalPage;
    }

    @GetMapping("/list/pagination/{page}")
    public PaginationList Pagination(@PathVariable int page){
        PaginationList paginationList = new PaginationList();
        int offset = 5;
        int totalItems = projectService.allItems();
        List<Project> projects = projectService.pagination(page);
        int totalPage;
        if(totalItems%offset == 0){
            totalPage = totalItems/offset;
        }else {
            totalPage = totalItems / offset + 1;
        }
        paginationList.setActivePage(1);
        paginationList.setTotalPage(totalPage);
        paginationList.setProjects(projects);
        return paginationList;
    }


    @PutMapping("/list/pagination/{page}")
    public PaginationList paginationPut(@PathVariable int page){
        PaginationList paginationList = new PaginationList();
        int offset = 5;
        int totalItems = projectService.allItems();
        List<Project> projects = (List<Project>) projectService.pagination(page);
        int totalPage;
        if(totalItems/offset == 0){
            totalPage = totalItems/offset;
        }else {
            totalPage = totalItems / offset + 1;
        }
        paginationList.setActivePage(page);
        paginationList.setTotalPage(totalPage);
        paginationList.setProjects(projects);
        return paginationList;
    }


    @PutMapping("/list/detail/{id}")
    public List<Employee> findEmployee(@PathVariable Long id){
        List<Employee> employeeList = new ArrayList<>();
        //List<Details> detailsList = new ArrayList<>();
        List<Details> detailsList = detailsService.findAllByProjectId(id);
        for(Object o : detailsList){
            Employee employee = new Employee();
            Details details = new Details();
            details = (Details) o;
           try{
               if(employeeService.findEmployeeById(details.getDetailEmployeeId()) != null){
                   employee = employeeService.findEmployeeById(details.getDetailEmployeeId());
                   employeeList.add(employee);
               }
           }finally {
               continue;
           }
        }
        return employeeList;
    }

    //searching
    @PutMapping("/list/searchPro/{log}")
    public List<Project> searchingProject(@PathVariable String log){
        return projectService.searchProject(log);
    }

}


