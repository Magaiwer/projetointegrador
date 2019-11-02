package projetointegrador.mail;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Email {

    private String sender;
    private List<String> recipients;
    private String subject;
    private String body;
    private String attachment;
    private String replyTo;

}
