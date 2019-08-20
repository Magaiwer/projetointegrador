package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import projetointegrador.Util.EFxmlView;
import projetointegrador.config.StageManager;
import projetointegrador.service.UsuarioService;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AppController implements Initializable {

    @FXML
    private VBox sidebar;

    @FXML
    private FontAwesomeIconView menuAvatar;

    @FXML
    private JFXButton menuUser;

    @FXML
    private JFXButton menuGroup;

    @FXML
    private AnchorPane body;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    void showUser(ActionEvent event) {
        stageManager.switchScene(body, EFxmlView.USER_TABLE);
    }

    @FXML
    void showGroup(ActionEvent event) {
        stageManager.switchScene(body, EFxmlView.GROUP_TABLE);
    }

    @FXML
    void showAudit(ActionEvent event) {
    }

    @FXML
    void showForms(ActionEvent event) {
        stageManager.switchScene(body, EFxmlView.FORM_TABLE);
    }


}