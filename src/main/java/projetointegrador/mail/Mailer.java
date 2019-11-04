package projetointegrador.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

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
            helper.setTo(email.getRecipients().toArray(new String[email.getRecipients().size()]));
            helper.setCc(email.getCopyTo().toArray(new String[email.getCopyTo().size()]));
            helper.setSubject(email.getSubject());
            helper.setReplyTo(email.getReplyTo() == null ? email.getSender() : email.getReplyTo());
            helper.setText(email.getBody());

            if (email.hasAttachments()) {
                addAttachment(email, helper);
            }

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("Error send  e-mail", e);
        }
    }

    private void addAttachment(Email email, MimeMessageHelper helper)  {
        email.getAttachments().forEach(file -> {
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            try {
                helper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
            } catch (MessagingException e) {
                logger.error("Error adding attachment ", e);
            }
        });
    }



}
