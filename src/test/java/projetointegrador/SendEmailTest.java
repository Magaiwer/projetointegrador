package projetointegrador;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import projetointegrador.mail.Email;
import projetointegrador.mail.Mailer;
import projetointegrador.service.BeanUtil;

import java.util.Arrays;

public class SendEmailTest {

    @Autowired
    private Mailer mailer;

    @Test
    public void sendEmailWithAttachment() {

        Email email = new Email();

        email.setSender("Magaiver <noreply@hotmail.com>");
        email.setRecipients(Arrays.asList("magaiwer@hotmail.com.br"));
        email.setSubject("Teste de envio de email");
        email.setBody("Este é um teste de envio de email!");
        //email.setAttachment();

        mailer.send(email);
    }
}
