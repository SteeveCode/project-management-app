package com.brexson.projectmanagementapp.controller;

import com.brexson.projectmanagementapp.entities.Employee;
import com.brexson.projectmanagementapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService empService;

    @GetMapping
    public String displayEmployees(Model model) {
        Iterable<Employee> employees = empService.getAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {
        Employee anEmployee = new Employee();
        model.addAttribute("employee", anEmployee);

        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(Model model, Employee employee, Errors errors) {

        if(errors.hasErrors())
            return "employees/new-employee";

        // save to the database using an employee crud repository
        empService.save(employee);

        // use a redirect to prevent duplicate submissions
        // always use a redirect after saving data
        return "redirect:/employees";
    }

    @GetMapping("/update")
    public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {

        Employee theEmp = empService.findByEmployeeId(theId);

        model.addAttribute("employee", theEmp);


        return "employees/new-employee";
    }
    @GetMapping("delete")
    public String deleteEmployee(@RequestParam("id") long theId, Model model) {
        Employee theEmp = empService.findByEmployeeId(theId);
        empService.delete(theEmp);
        return "redirect:/employees";
    }

}

