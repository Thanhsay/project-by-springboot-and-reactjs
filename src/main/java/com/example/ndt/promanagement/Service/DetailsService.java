package com.example.ndt.promanagement.Service;

import com.example.ndt.promanagement.Model.Details;
import com.example.ndt.promanagement.Repo.DetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsService {
    @Autowired
    DetailsRepo detailsRepo;
    //find by project_id
    public List<Details> findAllByProjectId(Long id){
        return detailsRepo.findByProjectId(id);
    }
    //Save details
    public Details detailsSave(Details details){
        return detailsRepo.save(details);
    }
    //Delete details
    public boolean deleteDetails(Details details){
        try{
            detailsRepo.delete(details);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    //find details_id by project_id and employee_id
    public List<Details> findDetails(Long project_id, Long employee_id){
        return detailsRepo.findByProjectAndEmployee(project_id, employee_id);
    }
}
