package com.best;

import com.best.service.MailServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZcdnApplicationTests {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MailServiceImpl mailService;

	@Test
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("dyc87112@qq.com");
		message.setTo("dyc87112@qq.com");
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");

		mailSender.send(message);
	}
	// @param sender 发件人别名
	// @param subject 邮件主题
	//@param content 邮件内容
	//@param receiverList 接收者列表,多个接收者之间用","隔开
	//@param fileSrc 附件地址
	@Test
	public void contextLoads() {
		String sender = "lala";
		String subject = "入职通知";
		String content = "邮件内容";
		String receiverList =  "45520456@qq.com";
		String fileSrc = "C:\\Users\\EDZ\\Desktop\\fileUpload\\favicon.ico";
		mailService.send(sender,subject,content,receiverList,fileSrc);
	}

}
