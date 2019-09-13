package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import projetointegrador.Util.EFxmlView;
import projetointegrador.Util.MessagesUtil;
import projetointegrador.config.StageManager;
import projetointegrador.annotation.Restriction;
import projetointegrador.security.Security;

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
    private JFXButton menuAudit;

    @FXML
    private JFXButton menuForm;

    @FXML
    private JFXButton menuMaterialAbsortancia;

    @FXML
    private JFXButton menuMaterial;

    @FXML
    private JFXButton menuPermissions;

    @FXML
    private AnchorPane body;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    @Restriction(value = "ROLE_VIEW_USER")
    void showUser(ActionEvent event) {
        if (Security.userHasRole("ROLE_VIEW_USER")) {
            stageManager.switchScene(body, EFxmlView.USER_TABLE);
        } else {
            MessagesUtil.showMessageWarning("Sem permissão de acesso!");
        }
    }

    @FXML
    @Restriction(value = "ROLE_VIEW_GROUP")
    void showGroup(ActionEvent event) {
        if(Security.userHasRole("ROLE_VIEW_GROUP")) {
            stageManager.switchScene(body, EFxmlView.GROUP_TABLE);
        } else {
            MessagesUtil.showMessageWarning("Sem permissão de acesso!");
        }
    }

    @FXML
    @Restriction(value = "ROLE_VIEW_AUDIT")
    void showAudit(ActionEvent event) {
        if(Security.userHasRole("ROLE_VIEW_AUDIT")) {
            stageManager.switchScene(body, EFxmlView.AUDITORIA_LIST);
        } else {
            MessagesUtil.showMessageWarning("Sem permissão de acesso!");
        }
    }

    @FXML
    @Restriction(value = "ROLE_VIEW_FORM")
    void showForms(ActionEvent event) {
        if(Security.userHasRole("ROLE_VIEW_FORM")) {
            stageManager.switchScene(body, EFxmlView.FORM_TABLE);
        } else {
            MessagesUtil.showMessageWarning("Sem permissão de acesso!");
        }
    }

    @FXML
    @Restriction(value = "ROLE_VIEW_ABSORTANCIA")
    void showMaterialAbsortancia(ActionEvent event) {
        if(Security.userHasRole("ROLE_VIEW_ABSORTANCIA")) {
            stageManager.switchScene(body, EFxmlView.MATERIAL_ABSORTANCIA_TABLE);
        } else {
            MessagesUtil.showMessageWarning("Sem permissão de acesso!");
        }
    }

    @FXML
    void showMaterial(ActionEvent event) {
        stageManager.switchScene(body, EFxmlView.MATERIAL_TABLE);
    }

    @FXML
    void showPermissions(ActionEvent event) {
        stageManager.switchScene(body, EFxmlView.PERMISSIONS_TABLE);
    }


}