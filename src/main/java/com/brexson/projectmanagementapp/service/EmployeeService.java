package com.brexson.projectmanagementapp.service;


import com.brexson.projectmanagementapp.dao.EmployeeRepository;
import com.brexson.projectmanagementapp.dto.EmployeeProject;
import com.brexson.projectmanagementapp.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepo;

    public Employee save(Employee employee) {
        return empRepo.save(employee);
    }

    public Iterable<Employee> getAll() {
        return empRepo.findAll();
    }

    public Iterable<Employee> getAll(Pageable pageAndSize ) {
        return empRepo.findAll(pageAndSize );
    }

    public List<EmployeeProject> employeeProjects() {
        return empRepo.employeeProjects();
    }

    public Employee findById(Long id){
        return empRepo.findById(id).get();
    }

    public void deleteById(Long id){
        empRepo.deleteById(id);
    }

    public Employee findByEmployeeId(long theId) {
        return empRepo.findByEmployeeId(theId);
    }
    public void delete(Employee theEmp) {
        empRepo.delete(theEmp);
    }

//	 Field Injection
//	@Qualifier("staffRepositoryImpl1")
//	@Autowired
//	IStaffRepository empRepo;

//	 Constructor Injection
//	public EmployeeService(@Qualifier("staffRepositoryImpl1")IStaffRepository empRepo) {
//		this.empRepo = empRepo;
//	}


//	 Setter Injection
//	@Autowired
//	(@Qualifier("staffRepositoryImpl1")
//	public void setEmpRepo(EmployeeRepository empRepo) {
//		this.empRepo = empRepo;
//	}

}
