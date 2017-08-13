package com.braj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braj.derby.entity.Employee;
import com.braj.derby.repo.EmployeeRepository;
import com.braj.mysql.entity.EmplDetails;
import com.braj.mysql.repo.EmpDetailRepository;
/**
* This controller class helps handle the request and response.
* @author  Basavaraj Angadi
* @version 1.0 
*/
@RestController
public class EmployeeController {
	

	@Autowired
	EmpDetailRepository mySqlEmplDetailRepo;
	@Autowired
	EmployeeRepository derbyEmplRepo;

	@RequestMapping("/getEmpInfoFromDerbyDbById/{id}")
	public Employee getEmpInfoFromDerbyDbById(@PathVariable("id") Long id) {
		Employee empl = derbyEmplRepo.findById(id);
		return empl;
	}

	@RequestMapping("/getEmpInfoFromMySqlDbById/{id}")
	public EmplDetails getEmpInfoFromMySqlDbById(@PathVariable("id") Long id) {
		EmplDetails emplDetail = mySqlEmplDetailRepo.findById(id);
		return emplDetail;
	}

}
