package com.bordza.booking.bordzaBooking;

import com.bordza.booking.bordzaBooking.services.MailService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BordzaBookingApplicationTests {

	@Autowired
	MailService mailService;


	@Test
	public void sendEmail() throws MessagingException {
		MimeMessage msg = mailService.buildEmail("mariehelene.delteil@gmail.com", "Hello", "blabla\ntruc", false);
		mailService.sendEmail(msg);
	}



}
