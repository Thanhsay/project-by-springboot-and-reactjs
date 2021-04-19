package com.example.ndt.promanagement.Service;

import com.example.ndt.promanagement.Model.Details;
import com.example.ndt.promanagement.Model.Project;
import com.example.ndt.promanagement.Repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepo projectRepo;

    public List<Project> projectFindAll(){
        return (List<Project>) projectRepo.findAll();
    }
    //add a new project
    public Project addNewProject(Project project){
        return projectRepo.save(project);
    }
    //find project by id
    public Project findProjectById(Long id){
        return projectRepo.findById(id).get();
    }

    public boolean deleteProject(Long id){
        projectRepo.deleteById(id);
        return true;
    }

    // Paginating and sort by project name
    public List<Project> pagination(int page){
        int offset = 5;
        Pageable pageable = PageRequest.of(page -1, offset, Sort.by("projectName").ascending());
        Page<Project> projects = projectRepo.findAll(pageable);
        List<Project> projectList = projects.getContent();
        return  projectList;
    }
    //get amount of all projects
    public int allItems(){
        return (int) projectRepo.count();
    }
    //searching
    public List<Project> searchProject(String log){
        return projectRepo.searchPro("%"+log+"%");
    }

}
