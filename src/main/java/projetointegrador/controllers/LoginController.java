package projetointegrador.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import projetointegrador.Util.EFxmlView;
import projetointegrador.config.StageManager;
import projetointegrador.model.User;
import projetointegrador.repository.UserRepository;
import projetointegrador.service.FormService;
import projetointegrador.service.UserService;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class LoginController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtUser;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Label lb_msg;


    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private FormService formService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtPassword.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    login();
                }
            }
        });
    }

    @FXML
    void autenthicate(ActionEvent event) {
        login();
    }

    private void login() {
        Optional<User> user = userRepository.findByLoginAndActiveTrue(txtUser.getText());

        if (user.isPresent()) {

            boolean isPasswordValid = bCryptPasswordEncoder.matches(txtPassword.getText(), user.get().getPassword());

            if (isPasswordValid) {
                Long id = user.get().getId();
                UserService.userLogged = userRepository.findUserWithGroups(id).get();

                formService.loadForms();
                MDC.put("user", user.get().getName());

                stageManager.switchScene(EFxmlView.HOME);
            } else {
                lb_msg.setText("Senha não confere");
                lb_msg.setVisible(true);
            }

        } else {
            lb_msg.setText("Usuário não encontrado");
            lb_msg.setVisible(true);
        }
    }

}
