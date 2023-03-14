package com.brexson.projectmanagementapp.controller;

import com.brexson.projectmanagementapp.dto.ChartData;
import com.brexson.projectmanagementapp.dto.EmployeeProject;
import com.brexson.projectmanagementapp.entities.Project;
import com.brexson.projectmanagementapp.service.EmployeeService;
import com.brexson.projectmanagementapp.service.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class HomeController {
    @Value("${version}")
    private String ver;
    @Autowired
    ProjectService proService;
    @Autowired
EmployeeService empService;
    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

        model.addAttribute("versionNumber", ver);

        // We are querying the database for projects
        List<Project> projects = proService.getAll();
        model.addAttribute("projectsList", projects);

        List<ChartData> projectData = proService.getProjectStatus();

        // Lets convert projectData object into a json structure to use in javascript

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        // [["NOTSTARTED",1], ["INPROGRESS",2], ["COMPLETED",1]]

        model.addAttribute("projectStatusCnt", jsonString);

        // We are querying the database for employees

        List<EmployeeProject> employeesProjectCnt = empService.employeeProjects();
        model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);

//		List<EmpPrjCnt> empProjectCnt = empRepo.EmpPrjCnt();
//		model.addAttribute("empListProjectCnt", empProjectCnt);

        for(EmployeeProject emp:employeesProjectCnt){
            System.out.println("First Name: " + emp.getFirstName());
            System.out.println("Project Count: " + emp.getProjectCount());
        }
        return "main/home";

    }
}
