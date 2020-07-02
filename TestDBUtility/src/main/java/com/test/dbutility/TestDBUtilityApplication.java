package com.test.dbutility;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.test.dbutility.db1.model.Customer;
import com.test.dbutility.db1.repository.CustomerRepository;
import com.test.dbutility.db2.model.Department;
import com.test.dbutility.db2.repository.DepartmentRepository;

@SpringBootApplication
@EnableScheduling
public class TestDBUtilityApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	DepartmentRepository deptRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(TestDBUtilityApplication.class, args);
	}
	
    @Override
    public void run(String... args) throws Exception {
    	List<Department> departments = Arrays.asList(new Department(1l, "HR"), new Department(2l, "IT"));
    	deptRepo.save(departments);
    	
    	List<Customer> customers = Arrays.asList(
    			new Customer(1l, "Kesavalu",1),
    			new Customer(2l, "Sundar",2),
    			new Customer(3l, "Prem",2),
    			new Customer(4l, "Shiva",2),
    			new Customer(5l, "Sai",1),
    			new Customer(6l, "Srini",1));
    	
		// save a list of Customers
    	custRepo.save(customers);
    }	
}
