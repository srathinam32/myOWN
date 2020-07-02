package com.test.dbutility.db1.repository;

import org.springframework.data.repository.CrudRepository;

import com.test.dbutility.db1.model.Customer;
 
public interface CustomerRepository extends CrudRepository<Customer, Long>{
}
