package com.braj.mysql.repo;

import org.springframework.data.repository.CrudRepository;

import com.braj.mysql.entity.EmplDetails;
/**
* Repository  connect to database to provide data.
* @author  Basavaraj Angadi
* @version 1.0 
*/
public interface EmpDetailRepository extends CrudRepository<EmplDetails, Long> {
	EmplDetails findById(Long id);
	EmplDetails findByName(String name);
}
