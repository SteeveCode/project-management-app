package com.brexson.projectmanagementapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_seq")
    @SequenceGenerator(name = "project_seq", sequenceName = "project_seq",
            allocationSize = 1,initialValue=1)
    private long projectId;
    private String name;
    private String stage; // NOTSTARTED, COMPLETED, INPROGRESS
    private String description;
//    @NotBlank(message="date cannot be empty")
    private LocalDate startDate;

//    @NotBlank(message="date cannot be empty")
    private LocalDate endDate;

    @ManyToMany(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name="project_employee",
            joinColumns=@JoinColumn(name="project_id"),
            inverseJoinColumns=@JoinColumn(name="employee_id"))

    @JsonIgnore
    private List<Employee> employees;

    public Project() {}

    public Project(String name, String stage, String description) {
        super();
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    public long getProjectId() {
        return projectId;
    }
    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    // convenience method
    public void addEmployee(Employee emp) {
        if(employees == null) {
            employees = new ArrayList<>();
        }
        employees.add(emp);
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


}
