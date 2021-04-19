package com.example.ndt.promanagement.Repo;

import com.example.ndt.promanagement.Model.Details;
import com.example.ndt.promanagement.Model.Employee;
import com.example.ndt.promanagement.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsRepo extends CrudRepository<Details, Long>, PagingAndSortingRepository<Details, Long> {
    @Query("SELECT u from Details u WHERE u.detailProjectId = ?1")
    List<Details> findByProjectId(Long id);

    @Query("SELECT u from Details u WHERE u.detailProjectId =?1 AND u.detailEmployeeId=?2")
    List<Details> findByProjectAndEmployee(Long project_id, Long employee_id);

    @Query("SELECT u from Details u WHERE u.detailEmployeeId =?1")
    List<Details> findByEmployeeId(Long id);
}
