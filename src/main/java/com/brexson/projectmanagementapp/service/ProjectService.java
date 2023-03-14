package com.brexson.projectmanagementapp.service;


import com.brexson.projectmanagementapp.dao.ProjectRepository;
import com.brexson.projectmanagementapp.dto.ChartData;
import com.brexson.projectmanagementapp.dto.TimeChartData;
import com.brexson.projectmanagementapp.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository proRepo;
    public Project save(Project project) {
        return proRepo.save(project);
    }
    public List<Project> getAll() {
        return proRepo.findAll();
    }
    public Project findById(Long id) {
        return proRepo.findById(id).get();
    }

    public Project findByProjectId(long theId) {
        return proRepo.findByProjectId(theId);
    }
    public void deleteById(Long id) {
        proRepo.deleteById(id);
    }


    public List<ChartData> getProjectStatus() {
        return proRepo.getProjectStatus();
    }

    public List<TimeChartData> getTimeData() {
        return proRepo.getTimeData();
    }
}
