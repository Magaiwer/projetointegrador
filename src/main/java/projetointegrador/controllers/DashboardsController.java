package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;
import projetointegrador.model.Project;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class DashboardsController implements Initializable
{
    @FXML
    private AnchorPane root;

    @FXML
    private JFXComboBox<Project> comboProject;

    @FXML
    private JFXComboBox<?> comboRegion;

    @FXML
    private JFXButton btnAddFilter;

    @FXML
    void onAddFilter(ActionEvent event)
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
