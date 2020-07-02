package com.test.dbutility.db2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departments",schema = "db2")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
 
	public Department() {
	}
 
	public Department(Long id, String name) {
		this.id = id;
		this.name = name;
	}
 
	public Long getId() {
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
  
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
 
}