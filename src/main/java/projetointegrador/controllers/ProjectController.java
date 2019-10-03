package projetointegrador.controllers;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import projetointegrador.Util.EFxmlView;
import projetointegrador.Util.MessagesUtil;
import projetointegrador.model.*;
import projetointegrador.model.Component;
import projetointegrador.repository.FaceRepository;
import projetointegrador.repository.PersonRepository;
import projetointegrador.repository.ProjectRepository;
import projetointegrador.repository.RoomRepository;
import projetointegrador.repository.RegionRepository;
import projetointegrador.service.ProjectService;
import projetointegrador.validation.EntityValidator;

import java.awt.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

@org.springframework.stereotype.Component
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
    private JFXComboBox<Person> comboCustumer;

    @FXML
    private JFXComboBox<Region> comboRegion;

    @FXML
    private JFXTextField txtIndex;

    @FXML
    private Tab tabRoom;

    @FXML
    private JFXTextField txtNameRoom;

    @FXML
    private TableView<Room> tableRoom;

    @FXML
    private TableColumn<Room, String> columnNameRoom;

    @FXML
    private JFXButton btnAddRoom;

    @FXML
    private Tab tabFace;

    @FXML
    private JFXTextField txtNameFace;

    @FXML
    private TableView<?> tableFace;

    @FXML
    private TableColumn<Face, String> columnNameFace;

    @FXML
    private TableColumn<Face, String> columnFaceRoom;

    @FXML
    private JFXButton btnAddFace;

    @FXML
    private JFXComboBox<Room> comboRoom;

    @FXML
    private Tab tabLayer;

    @FXML
    private Tab tabLayerMaterial;

    @FXML
    private JFXComboBox<Face> comboFace;

    @FXML
    private JFXComboBox<Material> comboMaterial;

    @FXML
    private JFXTextField txtThickness;

    @FXML
    private TableView<Component> tableLayerMaterial;

    @FXML
    private TableColumn<Component, String> columnMaterialLayer;

    @FXML
    private TableColumn<Component, BigDecimal> columnThicknessLayer;

    @FXML
    private TableColumn<Component, String> columnFaceLayer;

    @FXML
    private JFXButton btnAddLayer;

    @FXML
    private Tab tabFinal;

    @FXML
    private JFXTextField txtTemperatureOutside;

    @FXML
    private JFXTextField txtTemperatureInside;

    @FXML
    private JFXComboBox<MaterialAbsortancia> comboAbsortance;

    @FXML
    private JFXRadioButton rgWinter;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private JFXRadioButton rgSummer;

    @FXML
    private JFXTextField txtAlpha;

    @FXML
    private JFXTextField txtNameLayer;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FaceRepository faceRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    private Project project = new Project();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCombo();
    }

    @FXML
    @Override
    public void onCancel(ActionEvent event) {

    }

    @FXML
    @Override
    public void onSave(ActionEvent event)
    {
        if(tabProject.isSelected())
        {
            boolean noEmpty = EntityValidator.noEmpty(txtNameProject, txtIndex);

            if (project != null && noEmpty)
            {
                bindProject();

                try
                {
                    projectService.save(project);
                    MessagesUtil.showMessageInformation("Projeto salvo com sucesso");

                }
                catch (Exception e)
                {
                    MessagesUtil.showMessageError(e.getMessage());
                }
            }

        }
        if(tabRoom.isSelected())
        {

        }
        if(tabFace.isSelected())
        {

        }
        if(tabLayer.isSelected())
        {

        }
        if(tabLayerMaterial.isSelected())
        {

        }
        if(tabFinal.isSelected())
        {

        }
    }

    private void bindProject()
    {
        project.setName(txtNameProject.getText());
        project.setDescription(txtDescription.getText());
        project.setPerson(comboCustumer.getValue());
        project.setRegion(comboRegion.getValue());
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

    private void initCombo() {
        FilteredList<Person> personFilteredList = new FilteredList<>(listPerson(), person -> true);
        comboCustumer.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            personFilteredList.setPredicate(person -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String filterLowerCase = newValue.toLowerCase();

                return person.getName().toLowerCase().contains(filterLowerCase);
            });
        });

        comboCustumer.setItems(personFilteredList);


        FilteredList<Room> roomFilteredList = new FilteredList<>(listRoom(), room -> true);

        comboRoom.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            roomFilteredList.setPredicate(room -> room.getName().toLowerCase().contains(newValue.toLowerCase()));
        });

        comboFace.setItems(listFace());

        comboRegion.setItems(listRegion());
    }

    @FXML
    void onNext(ActionEvent event) {
        this.onSave(event);
        tabPane.getSelectionModel().selectNext();
    }

    @FXML
    void onPrevious(ActionEvent event) {
        tabPane.getSelectionModel().selectPrevious();
    }

    private ObservableList<Person> listPerson() {
        return FXCollections.observableArrayList(personRepository.findAll());
    }

    private ObservableList<Room> listRoom() {
        return FXCollections.observableArrayList(roomRepository.findAll());
    }

    private ObservableList<Face> listFace() {
        return FXCollections.observableArrayList(faceRepository.findAll());
    }

    private ObservableList<Region> listRegion() {
        return FXCollections.observableArrayList(regionRepository.findAll());
    }
}
