package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import lombok.Getter;
import lombok.Setter;
import projetointegrador.model.Component;
import projetointegrador.model.Face;
import projetointegrador.model.Project;
import projetointegrador.model.Room;
import projetointegrador.repository.ComponentRepository;
import projetointegrador.repository.FaceRepository;
import projetointegrador.repository.RoomRepository;
import projetointegrador.repository.filter.ProjectFilter;
import projetointegrador.service.BeanUtil;
import projetointegrador.service.RoomService;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXComboBox<Room> comboRoom;

    @FXML
    private JFXComboBox<Face> comboFace;

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

    @FXML
    private TableColumn<Component, BigDecimal> columnM2;

    @FXML
    private TableColumn<Component, BigDecimal> columnRSI;

    @FXML
    private JFXTextField txtBTUS;

    @FXML
    private Label lbDescriptionForm;

    @FXML
    private JFXButton btnClear;

    private FaceRepository faceRepository;
    private RoomRepository roomRepository;
    private ComponentRepository componentRepository;

    @Getter
    @Setter
    private Project project;

    private ProjectFilter projectFilter;

    public DetailController(Project project) {
        this.project = project;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        roomRepository = BeanUtil.getBean(RoomRepository.class);
        faceRepository = BeanUtil.getBean(FaceRepository.class);
        componentRepository = BeanUtil.getBean(ComponentRepository.class);

        projectFilter = new ProjectFilter();

        if (this.project != null) {
            lbDescriptionForm.setText("Detalhes do Projeto: " + project.getIdProjectAndName());
            comboRoom.setItems(listRoomByProject());
            projectFilter.setProjectId(project.getId());
            initListeners();
            initTable(projectFilter);
            initConverter();
        }
    }

    @FXML
    void onBtnClearClick(ActionEvent event) {
        comboRoom.getSelectionModel().clearSelection();
        comboFace.getSelectionModel().clearSelection();
        txtBTUS.clear();
        projectFilter = new ProjectFilter();
        projectFilter.setProjectId(project.getId());
        initTable(projectFilter);
    }

    private ObservableList<Room> listRoomByProject() {
        return FXCollections.observableArrayList(roomRepository.findByProjectWithFaces(project.getId()));
    }

    private ObservableList<Face> listFaceByRoom(Room room) {
        return FXCollections.observableArrayList(faceRepository.findByIdRoom(room.getId()));
    }

    private ObservableList<Component> listDetailProject(ProjectFilter filter) {
        return FXCollections.observableArrayList(componentRepository.findByProject(filter));
    }


    private void initListeners() {
        comboRoom.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                calculateBTUS(newValue);
                comboFace.setItems(listFaceByRoom(newValue));
                projectFilter = new ProjectFilter();
                projectFilter.setRoomId(newValue.getId());
                initTable(projectFilter);
            }
        });

        comboFace.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                projectFilter.setFaceId(newValue.getId());
                initTable(projectFilter);
            }
        });

    }

    private void calculateBTUS(Room room) {
        txtBTUS.setText(room.calculateBtus().toString());
    }

    private void initTable(ProjectFilter filter) {
        columnRoom.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFace().getRoom().getName()));
        columnFace.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFace().getName()));
        columnThermalLoad.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getFace().getThermalLoad()));
        columnComponent.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnTransmittance.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getTransmittance()));
        columnQfo.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getQfo()));
        columnQft.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getQft()));
        columnResistance.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getResistanceTotal()));
        columnM2.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getM2()));

        tableDetail.setItems(listDetailProject(filter));
    }

    private void initConverter() {
        comboRoom.setConverter(new StringConverter<Room>() {
            @Override
            public String toString(Room room) {
                return room.getName();
            }

            @Override
            public Room fromString(String string) {
                return null;
            }
        });

        comboFace.setConverter(new StringConverter<Face>() {
            @Override
            public String toString(Face face) {
                return face.getName();
            }

            @Override
            public Face fromString(String string) {
                return null;
            }
        });

    }
}
