package com.test.dbutility.db2.repository;

import org.springframework.data.repository.CrudRepository;

import com.test.dbutility.db1.model.Customer;
import com.test.dbutility.db2.model.Department;
 
public interface DepartmentRepository extends CrudRepository<Department, Long>{
}
