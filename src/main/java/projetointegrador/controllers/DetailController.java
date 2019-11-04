package projetointegrador.controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import projetointegrador.model.Component;
import projetointegrador.model.Project;
import projetointegrador.model.Room;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXComboBox<Room> comboRoom;

    @FXML
    private JFXComboBox<?> comboFace;

    @FXML
    private TableView<Component> tableDetail;

    @FXML
    private TableColumn<Component, String> columnRoom;

    @FXML
    private TableColumn<Component, String> columnFace;

    @FXML
    private TableColumn<Component, BigDecimal> columnThermalLoad;

    @FXML
    private TableColumn<Component, String> columnComponent;

    @FXML
    private TableColumn<Component, BigDecimal> columnTransmittance;

    @FXML
    private TableColumn<Component, BigDecimal> columnResistance;

    @FXML
    private TableColumn<Component, BigDecimal> columnQfo;

    @FXML
    private TableColumn<Component, BigDecimal> columnQft;

    @Getter @Setter
    private Project project;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
