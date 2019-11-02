package projetointegrador.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import projetointegrador.service.BeanUtil;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class Mailer {
	
	private static Logger logger = LoggerFactory.getLogger(Mailer.class);

	@Autowired
	private JavaMailSender mailSender;
	

	@Async
	public void send(Email email) {
	try {

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");


			helper.setFrom(email.getSender());
			helper.setTo(email.getRecipients().stream().toArray(String[]::new));
			helper.setSubject(email.getSubject());
			helper.setReplyTo(email.getReplyTo() == null ? email.getSender() : email.getReplyTo());
			helper.setText(email.getBody());
			
			//helper.addInline("logo", new ClassPathResource("static/images/logo-gray.png"));


			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Error send  e-mail", e);
		}
	}
	
}
