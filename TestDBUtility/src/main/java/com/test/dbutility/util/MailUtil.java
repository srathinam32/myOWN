/**
 * 
 */
package com.test.dbutility.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


/**
 * @author Kesavalu
 *
 */
@Component
public class MailUtil {
	
	@Autowired
    JavaMailSender javaMailSender;
	
	public void sendEmail(String fromAddress,String[] toAddress,String subject,String textBody) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromAddress);
        msg.setTo(toAddress);

        msg.setSubject(subject);
        msg.setText(textBody);

        javaMailSender.send(msg);

    }
	public void sendEmailWithAttachment(String fromAddress,String[] toAddress,String subject,String textBody, File file,String fileName) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
        helper.setTo(toAddress);

        helper.setSubject(subject);

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText(textBody, true);

		// hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));
        if(file!= null)
        	helper.addAttachment(fileName, file);

        javaMailSender.send(msg);
        System.out.println("Mail sent");

    }
}
