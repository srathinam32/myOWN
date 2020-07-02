package com.test.dbutility.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.dbutility.db1.model.Customer;
import com.test.dbutility.db1.repository.CustomerRepository;
import com.test.dbutility.db2.model.Department;
import com.test.dbutility.db2.repository.DepartmentRepository;
import com.test.dbutility.dto.CustomerDetail;
import com.test.dbutility.service.DBUtilityService;
import com.test.dbutility.util.ExcelGenerator;
import com.test.dbutility.util.MailUtil;

@RestController
@RequestMapping("/api/customers")
public class CustomerResource {
	
@Autowired
DBUtilityService service;
	
	@GetMapping(value = "/downloadReport")
	public ResponseEntity<InputStreamResource> downloadReport() throws IOException {
		List<CustomerDetail> customerDetails = service.getCustomerDetail();
		
		ByteArrayInputStream in = ExcelGenerator.customersToExcel(customerDetails);
		// return IOUtils.toByteArray(in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=customers.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@GetMapping(value = "/sendMail")
	public ResponseEntity<String> mail(@RequestParam String mail) throws IOException {
		System.out.println("email value=" + mail);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=customers.xlsx");
		service.sendMail(mail,"");
			return ResponseEntity.ok().headers(headers).body("Email sent to " + mail + " successfully");
	}
}
