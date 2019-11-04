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
import projetointegrador.annotation.Restriction;
import projetointegrador.config.StageManager;

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
    private JFXButton menuProject;

    @FXML
    private JFXButton menuDashboards;

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
        stageManager.switchScene(body, EFxmlView.USER_TABLE);
    }

    @FXML
    @Restriction(value = "ROLE_VIEW_GROUP")
    void showGroup(ActionEvent event) {
        stageManager.switchScene(body, EFxmlView.GROUP_TABLE);
    }

    @FXML
    @Restriction(value = "ROLE_VIEW_AUDIT")
    void showAudit(ActionEvent event) {
        stageManager.switchScene(body, EFxmlView.AUDITORIA_LIST);
    }

    @FXML
    @Restriction(value = "ROLE_VIEW_FORM")
    void showForms(ActionEvent event) {
        stageManager.switchScene(body, EFxmlView.FORM_TABLE);
    }

    @FXML
    @Restriction(value = "ROLE_VIEW_ABSORTANCIA")
    void showMaterialAbsortancia(ActionEvent event) {
        stageManager.switchScene(body, EFxmlView.MATERIAL_ABSORTANCIA_TABLE);
    }

    @FXML
    void showMaterial(ActionEvent event) {
        stageManager.switchScene(body, EFxmlView.MATERIAL_TABLE);
    }

    @FXML
    void showPermissions(ActionEvent event) {
        stageManager.switchScene(body, EFxmlView.PERMISSIONS_TABLE);
    }

    @FXML
    void showProject(ActionEvent event) {
        stageManager.switchScene(body, EFxmlView.PROJECT_TABLE);
    }

    @FXML
    void showDashboards(ActionEvent event) {
        stageManager.switchScene(body, EFxmlView.DASHBOARDS);
    }


}