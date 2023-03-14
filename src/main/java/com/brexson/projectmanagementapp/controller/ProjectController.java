package com.brexson.projectmanagementapp.controller;


import com.brexson.projectmanagementapp.dto.TimeChartData;
import com.brexson.projectmanagementapp.entities.Employee;
import com.brexson.projectmanagementapp.entities.Project;
import com.brexson.projectmanagementapp.service.EmployeeService;
import com.brexson.projectmanagementapp.service.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    ProjectService proService;

    @Autowired
    EmployeeService empService;

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = proService.getAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {

        Project aProject = new Project();
        model.addAttribute("project", aProject);

        Iterable<Employee> employees = empService.getAll();
        model.addAttribute("allEmployees", employees);

        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(@RequestParam("startDate") String startDate,
                                @RequestParam("endDate") String endDate,
                                Project project, Model model) {
//        LocalDate formatteStartdDate = null;
//        LocalDate formatteEnddDate = null;
//        DateTimeFormatter dateTimeFormatter;
//        dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
//
//        formatteStartdDate = formatteStartdDate.parse(String.format(startDate, dateTimeFormatter));
//        formatteEnddDate = formatteEnddDate.parse(String.format(endDate, dateTimeFormatter));
//
//        project.setStartDate(formatteStartdDate);
//        project.setEndDate(formatteEnddDate);
        System.out.println(startDate);
        System.out.println(endDate);

        proService.save(project);

//		The code below is no longer needed because we changed the relationship from @ManyToOne
//		to @ManyToMany. Hence we don't have a foreign key column in employee table anymore
//		so no need to populate it with project_id values from project table.

//		Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);
//
//		for(Employee emp : chosenEmployees){
//			emp.setProject(project);
//			empRepo.save(emp);
//		}
        return "redirect:/projects/new";
    }

    @GetMapping("/update")
    public String displayProjectUpdateForm(@RequestParam("id") long theId, Model model) {
        Project thePro = proService.findByProjectId(theId);
        model.addAttribute("project", thePro);

        return "projects/new-project";
    }
    @GetMapping("delete")
    public String deleteProject(@RequestParam("id") long theId, Model model) {
        Project thePro = proService.findByProjectId(theId);
        proService.deleteById(theId);
        return "redirect:/projects";
    }
    @GetMapping("/timelines")
    public String displayProjectTimeLines(Model model) throws JsonProcessingException {
        List<TimeChartData> timeLineData = proService.getTimeData();
        ObjectMapper objectMapper = new ObjectMapper();
        String jasonTimeLineSting =objectMapper.writeValueAsString(timeLineData);

        System.out.println("===========project timelines===========");
        System.out.println(jasonTimeLineSting);
        model.addAttribute("projectTimeList", jasonTimeLineSting);

        return "projects/project-timelines";
    }

}
