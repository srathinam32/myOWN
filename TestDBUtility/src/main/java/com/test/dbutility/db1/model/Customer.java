package com.test.dbutility.db1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers",schema = "db1")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="dept_id")
	private long deptId;
	
	public Customer() {
	}
 
	public Customer(Long id, String name,long deptId) {
		this.id = id;
		this.name = name;
		this.deptId = deptId;
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
		return "Customer [id=" + id + ", name=" + name + ", deptId= "+deptId+"]";
	}

	/**
	 * @return the deptId
	 */
	public long getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}
 
}