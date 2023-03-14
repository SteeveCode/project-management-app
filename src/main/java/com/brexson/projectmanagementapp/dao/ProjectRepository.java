package com.brexson.projectmanagementapp.dao;


import com.brexson.projectmanagementapp.dto.ChartData;
import com.brexson.projectmanagementapp.dto.TimeChartData;
import com.brexson.projectmanagementapp.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Override
    public List<Project> findAll();

    public Project findByProjectId(long theId);

    @Query(nativeQuery=true, value="SELECT stage as label, COUNT(*) as count FROM project GROUP BY stage")
    public List<ChartData> getProjectStatus();
    @Query(nativeQuery=true, value="SELECT name as projectName, start_date as startDate, end_date as endDate"
            + " FROM project WHERE start_date is not null")
    public List<TimeChartData> getTimeData();

}
