package com.example.ndt.promanagement.Repo;

import com.example.ndt.promanagement.Model.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends PagingAndSortingRepository<Project, Long> {
    @Query("SELECT u FROM Project u WHERE u.projectName like ?1")
    List<Project> searchPro(String log);
}
