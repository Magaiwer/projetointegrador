package projetointegrador.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Email {

    private String sender;
    private List<String> recipients;
    private List<String> copyTo;
    private List<String> attachments;
    private String subject;
    private String body;
    private String replyTo;


    public void addRecipients(String recipient) {
        if (this.recipients == null) {
            this.recipients = new ArrayList<>();
        }
        if (!StringUtils.isEmpty(recipient)) {
            this.recipients = Arrays.asList(recipient.split(";"));
        }
    }

    public void addCopyTo(String copyTo) {
        if (this.copyTo == null) {
            this.copyTo = new ArrayList<>();
        }

        if (!StringUtils.isEmpty(copyTo)) {
            this.copyTo = Arrays.asList(copyTo.split(";"));
        }

    }

    public void addAttachments(String path) {
        if (this.attachments == null) {
            this.attachments = new ArrayList<>();
        }

        if (!StringUtils.isEmpty(path)) {
            this.attachments.add(path);
        }
    }

    public boolean hasAttachments() {
        return this.attachments != null && !this.attachments.isEmpty();
    }


}
