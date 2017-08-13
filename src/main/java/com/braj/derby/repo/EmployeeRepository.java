package com.braj.derby.repo;

import org.springframework.data.repository.CrudRepository;

import com.braj.derby.entity.Employee;
/**
* Repository  connect to database to provide data.
* @author  Basavaraj Angadi
* @version 1.0 
*/
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	Employee findById(Long id);

}
