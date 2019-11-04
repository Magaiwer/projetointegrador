package projetointegrador.controllers;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import projetointegrador.mail.Email;
import projetointegrador.mail.Mailer;
import projetointegrador.model.Group;
import projetointegrador.model.Project;
import projetointegrador.service.BeanUtil;
import projetointegrador.service.UserService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EmailController implements Initializable {

    @FXML
    private JFXTextField txtSender;

    @FXML
    private JFXTextField txtRecepients;

    @FXML
    private JFXTextField txtSubject;

    @FXML
    private JFXTextArea txtBody;

    @FXML
    private JFXTextField txtCc;

    @FXML
    private JFXTextField txtAttachment;

    @FXML
    private JFXButton btnAttach;

    @FXML
    private JFXButton btnSendMail;

    @FXML
    private JFXChipView<String> chipViewRecipients;

    @FXML
    private JFXChipView<File> chipViewAttach;

    @FXML
    private JFXListView<Label> listViewAttach;

    @Autowired
    private Mailer mailer;

    @Getter @Setter
    private Project project;

    private Email email;


    public EmailController(Project project) {
        this.project = project;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        email = new Email();

        initConverter();

        if(project != null) {
            txtSender.setText(UserService.userLogged.getLogin());
            txtSubject.setText(project.getName());
            txtRecepients.setText(project.getPerson().getEmail());
        }

        btnAttach.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());

            if (!chipViewAttach.getChips().contains(file)) {
                chipViewAttach.getChips().add(file);
                chipViewAttach.setVisible(true);
            }
        });

        mailer = BeanUtil.getBean(Mailer.class);
        btnSendMail.setOnAction(event -> {
            bindEmail();
            mailer.send(email);
        });

    }

    private void bindEmail() {
        email = new Email();
        email.setSender(txtSender.getText());
        email.setSubject(txtSubject.getText());
        email.addCopyTo(txtCc.getText());
        email.addRecipients(txtRecepients.getText());
        email.setBody(txtBody.getText());

        if (!chipViewAttach.getChips().isEmpty()) {
            chipViewAttach.getChips().forEach(file -> email.addAttachments(file.getPath()));
        }
    }

    private void initConverter() {
        StringConverter<File> fileStringConverter = new StringConverter<File>() {
            @Override
            public String toString(File file) {
                return file.getName();
            }

            @Override
            public File fromString(String string) {
                return null;
            }
        };

        chipViewAttach.setConverter(fileStringConverter);
    }

}
