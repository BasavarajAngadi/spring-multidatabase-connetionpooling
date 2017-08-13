package com.braj.derby.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
* Entity class is mapped to employeinfo table.
* @author  Basavaraj Angadi
* @version 1.0 
*/
@Entity
@Table(name = "employeinfo")
public class Employee {
	@Id
	@Column(name = "ID")
	Long id;
	@Column(name = "name")
	String name;
	@Column(name = "lastname")
	String lastName;

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
