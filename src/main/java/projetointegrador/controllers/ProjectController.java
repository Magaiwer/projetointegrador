package projetointegrador.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TabPane;
import org.springframework.stereotype.Component;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ProjectController implements Initializable, BaseController<ProjectController> {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnPreviousProject;

    @FXML
    private JFXButton btnNextProject;

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private Tab tabProject;

    @FXML
    private JFXTextField txtNameProject;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXComboBox<?> comboCustumer;

    @FXML
    private Tab tabRoom;

    @FXML
    private JFXTextField txtNameRoom;

    @FXML
    private JFXButton btnAddRoom;

    @FXML
    private Tab tabFace;

    @FXML
    private JFXTextField txtNameFace;

    @FXML
    private JFXButton btnAddFace;

    @FXML
    private JFXComboBox<?> comboRoom;

    @FXML
    private Tab tabLayer;

    @FXML
    private JFXComboBox<?> comboFace;

    @FXML
    private JFXComboBox<?> comboMaterial;

    @FXML
    private JFXTextField txtThickness;

    @FXML
    private JFXButton btnAddLayer;

    @FXML
    private Tab tabFinal;

    @FXML
    private JFXTextField txtTemperatureOutside;

    @FXML
    private JFXTextField txtTemperatureInside;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    @Override
    public void onCancel(ActionEvent event) {

    }

    @FXML
    @Override
    public void onSave(ActionEvent event) {

    }

    @FXML
    @Override
    public void onEdit(ActionEvent event) {

    }

    @FXML
    @Override
    public void onDelete(ActionEvent event) {

    }

    @FXML
    @Override
    public void onNew(ActionEvent event) {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initTable() {

    }

    @FXML
    void onNext(ActionEvent event) {
       tabPane.getSelectionModel().selectNext();
    }

    @FXML
    void onPrevious(ActionEvent event) {
        tabPane.getSelectionModel().selectPrevious();
    }
}
