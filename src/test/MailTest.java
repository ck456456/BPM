package test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailTest {
	
	public static void main(String[] args) {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("Spring_Mail.xml") ;   
		JavaMailSender mailSender = (JavaMailSender) beanFactory.getBean("mailSender");
		/* 
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl(); 
		senderImpl.setHost("172.16.88.33"); 
		senderImpl.setUsername("86407"); 
		senderImpl.setPassword("2wwwwwwwww");
		*/
		SimpleMailMessage mailTXT = new SimpleMailMessage();
		// mailTXT.setTo("86407@prodisc.com.tw"); 
		mailTXT.setTo("ck456456@yahoo.com.tw"); 
		mailTXT.setSubject("測試TEXT郵件！"); 
		mailTXT.setFrom("86407@prodisc.com.tw"); 
		mailTXT.setText("This is the test message for testing gmail smtp server using spring mail. ");
		mailSender.send(mailTXT);
	}
}
