package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class EmailController  implements Initializable {

    @FXML
    private JFXTextField txtSender;

    @FXML
    private JFXTextField txtRecepients;

    @FXML
    private JFXTextField txtSubject;

    @FXML
    private JFXTextArea txtBody;

    @FXML
    private JFXTextField txtAttachment;

    @FXML
    private JFXButton btnAttach;

    @FXML
    private JFXButton btnSendMail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
