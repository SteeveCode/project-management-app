package com.brexson.projectmanagementapp.dao;

import com.brexson.projectmanagementapp.dto.EmployeeProject;
import com.brexson.projectmanagementapp.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel="apiemployees", path="apiemployees")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

// replacing this findAll() that returns a list with the invisible inbuilt findAll() that returns an iterable.
//	@Override
//	public List<Employee> findAll();

    @Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
    public List<EmployeeProject> employeeProjects();
    public Employee findByEmail(String value);
    public Employee findByEmployeeId(long theId);

}
