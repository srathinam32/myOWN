/**
 * 
 */
package com.test.dbutility.service;

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
import org.springframework.stereotype.Service;

import com.test.dbutility.db1.model.Customer;
import com.test.dbutility.db1.repository.CustomerRepository;
import com.test.dbutility.db2.model.Department;
import com.test.dbutility.db2.repository.DepartmentRepository;
import com.test.dbutility.dto.CustomerDetail;
import com.test.dbutility.util.ExcelGenerator;
import com.test.dbutility.util.MailUtil;

/**
 * @author Kesavalu
 *
 */
@Service
public class DBUtilityService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	MailUtil mailUtil;
	
	public List<CustomerDetail> getCustomerDetail(){
		Map<Long,String> departmentMap = new HashMap<Long,String>();
		List<CustomerDetail> customerDetails = new ArrayList<>();
		List<Department> departments = (List<Department>) departmentRepository.findAll();
		for (Department dept : departments) {
			departmentMap.put(dept.getId(), dept.getName());
		}
		

		List<Customer> customers = (List<Customer>) customerRepository.findAll();
		for (Customer cust : customers) {
			CustomerDetail custDet = new CustomerDetail();
			custDet.setId(cust.getId());
			custDet.setName(cust.getName());
			custDet.setDeptId(cust.getDeptId());
			custDet.setDeptName(departmentMap.get(cust.getDeptId()));
			customerDetails.add(custDet);
		}
		return customerDetails;
	}
	
	public void sendMail(String email,String msg) throws IOException {
		
		List<CustomerDetail> customerDetails = getCustomerDetail();
		ByteArrayInputStream in = null;
		File targetFile = null;
		OutputStream out = null;
		try {
			in = ExcelGenerator.customersToExcel(customerDetails);
			byte[] buffer = new byte[in.available()];
			in.read(buffer);

			targetFile = new File("temp.xls");
			out = new FileOutputStream(targetFile);
			out.write(buffer);
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}

		try {
			mailUtil.sendEmailWithAttachment("dummy.fmsproject@gmail.com",new String[] {email}, "Test Report "+msg, "test mail for report sending....", targetFile, "TestDBUtilityReport");
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
