package com.test.dbutility.scheduler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.test.dbutility.service.DBUtilityService;

@Component
public class Scheduler {
	
	@Value("${emailTo}")
	private String emailTo;
	
	@Autowired
	DBUtilityService service;
	
   @Scheduled(cron = "${scheduling.job.cron}")
   public void cronJobSch() throws IOException {
	  String msg = "from scheduler";
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      Date start = new Date();
      String strDate = sdf.format(start);
      System.out.println("Java cron job expression:: started" + strDate);
      service.sendMail(emailTo,msg+strDate);
      Date end = new Date();
      String endDate = sdf.format(end);
      System.out.println("Java cron job expression:: ended" + endDate);
      
      
   }
}