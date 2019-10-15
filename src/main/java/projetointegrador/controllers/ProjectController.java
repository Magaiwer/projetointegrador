package projetointegrador.controllers;

import com.jfoenix.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import projetointegrador.Util.EFxmlView;
import projetointegrador.Util.Formatter;
import projetointegrador.Util.MessagesUtil;
import projetointegrador.config.StageManager;
import projetointegrador.model.*;
import projetointegrador.repository.*;
import projetointegrador.service.FaceService;
import projetointegrador.service.ProjectService;
import projetointegrador.service.RoomService;
import projetointegrador.validation.EntityValidator;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private JFXComboBox<Person> comboCustomer;

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
    private TableView<Face> tableFace;

    @FXML
    private TableColumn<Face, String> columnFace;

    @FXML
    private TableColumn<Face, String> columnFaceRoom;

    @FXML
    private JFXButton btnAddFace;

    @FXML
    private JFXComboBox<Room> comboRoom;

    @FXML
    private Tab tabComponent;

    @FXML
    private JFXTextField txtNameComponent;

    @FXML
    private TableView<?> tableComponent;

    @FXML
    private TableColumn<Component, String> columnNameFace;

    @FXML
    private TableColumn<Component, String> columnNameComponent;

    @FXML
    private JFXButton btnAddComponent;

    @FXML
    private JFXComboBox<Face> comboFace;

    @FXML
    private Tab tabComponentMaterial;

    @FXML
    private JFXComboBox<Component> comboComponent;

    @FXML
    private JFXComboBox<Material> comboMaterial;

    @FXML
    private JFXTextField txtThickness;

    @FXML
    private TableView<ComponentMaterial> tableComponentMaterial;

    @FXML
    private TableColumn<ComponentMaterial, String> columnComponentMaterial;

    @FXML
    private TableColumn<ComponentMaterial, String> columnMaterialComponent;

    @FXML
    private TableColumn<ComponentMaterial, BigDecimal> columnThicknessComponent;

    @FXML
    private JFXButton btnAddComponentMaterial;

    @FXML
    private Tab tabFinal;

    @FXML
    private JFXTextField txtTemperatureOutside;

    @FXML
    private JFXTextField txtTemperatureInside;

    @FXML
    private JFXComboBox<MaterialAbsortancia> comboAbsorbance;

    @FXML
    private JFXRadioButton rgWinter;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private JFXRadioButton rgSummer;

    @FXML
    private JFXTextField txtAlpha;


    @FXML
    private TableView<Project> tableProject;

    @FXML
    private TableColumn<Project, Long> columnIdProject;

    @FXML
    private TableColumn<Project, String> columnNameProject;

    @FXML
    private TableColumn<Project, String> columnClientProject;

    @FXML
    private TableColumn<Project, String> columnRegionProject;

    @FXML
    private TableColumn<Project, String> columnDateProject;

    @FXML
    private JFXTextField txtFilterProject;

    @FXML
    private JFXButton btnNewProject;

    @FXML
    private JFXButton btnEditProject;

    @FXML
    private JFXButton btnDeleteProject;

    @FXML
    private JFXButton btnDetailProject;

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

    @Autowired
    private RoomService roomService;

    @Autowired
    private FaceService faceService;

    @Lazy
    @Autowired
    private StageManager stageManager;

    private Project project;
    private List<Room> listRoom;
    private List<Face> listFace;
    private Room room;
    private Face face;

    private void initializeFormWizzard() {

        if(txtIndex != null) {
            project = new Project();
            room = new Room();
            face = new Face();
            listRoom = new ArrayList<>();
            listFace = new ArrayList<>();

            initCombo();
            initTableRoom();
            initTableFace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        initializeFormWizzard();
    }

    @FXML
    @Override
    public void onCancel(ActionEvent event) {

    }

    @FXML
    void onAddRoom(ActionEvent event)
    {
        boolean noEmpty = EntityValidator.noEmpty(txtNameRoom);
        if(room != null && noEmpty)
        {
            bindRoom();
            listRoom.add(room);
            tableRoom.setItems(FXCollections.observableArrayList(listRoom));
        }
    }

    @FXML
    void onAddFace(ActionEvent event)
    {
        boolean noEmpty = EntityValidator.noEmpty(txtNameFace);
        if(face != null && noEmpty)
        {
            bindFace();
            listFace.add(face);
            tableFace.setItems(FXCollections.observableArrayList(listFace));
        }
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
            boolean noEmpty = EntityValidator.noEmpty(txtNameRoom);

            if(room != null && noEmpty)
            {
                bindRoom();

                try
                {
                    tableRoom.getItems().forEach(room-> roomService.save(room));
                    MessagesUtil.showMessageInformation("Comodo(s) salvo(s) com sucesso");
                }
                catch(Exception e)
                {
                    MessagesUtil.showMessageError(e.getMessage());
                }
            }
        }
        if(tabFace.isSelected())
        {
            boolean noEmpty = EntityValidator.noEmpty(txtNameFace);

            if(face != null && noEmpty)
            {
                bindFace();

                try
                {
                    tableFace.getItems().forEach(face-> faceService.save(face));
                    MessagesUtil.showMessageInformation("Face(s) salva(s) com sucesso");
                }
                catch(Exception e)
                {
                    MessagesUtil.showMessageError(e.getMessage());
                }
            }
        }
        if(tabComponent.isSelected())
        {

        }
        if(tabComponentMaterial.isSelected())
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
        project.setPerson(comboCustomer.getValue());
        project.setRegion(comboRegion.getValue());
    }

    private void bindRoom()
    {
        room = new Room();
        room.setProject(project);
        room.setName(txtNameRoom.getText());
    }

    private void bindFace()
    {
        face = new Face();
        face.setName(txtNameFace.getText());
        face.setRoom(room);
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
        stageManager.switchScene(root, EFxmlView.PROJECT);
        txtNameProject.requestFocus();
    }

    @Override
    public void initListeners() {

    }

    public void initTableRoom() {
        columnNameRoom.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public void initTableFace() {
        columnFace.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnFaceRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
    }

    @Override
    public void initTable() {
                     /*Table project*/
        columnIdProject.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNameProject.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnClientProject.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPerson().getName()));
        columnRegionProject.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getRegion().getName()));
        columnDateProject.setCellValueFactory(param -> new SimpleStringProperty(Formatter.getDateTextFormated(param.getValue().getDate(), Formatter.FORMAT_PT_BR_WITH_TIME)));

        FilteredList<Project> projectFilteredList = new FilteredList<>(listProject(), project -> true);

        txtFilterProject.textProperty().addListener((observable, oldValue, newValue) -> projectFilteredList.setPredicate(project -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String filterLowerCase = newValue.toLowerCase();
            return predicatePersonProjectRegion(project, filterLowerCase);
        }));

        SortedList<Project> projectSortedList = new SortedList<>(projectFilteredList);
        projectSortedList.comparatorProperty().bind(tableProject.comparatorProperty());
        tableProject.setItems(projectSortedList);

    }

    private boolean predicatePersonProjectRegion(Project project, String filterLowerCase) {
        String date = Formatter.getDateTextFormated(project.getDate(),Formatter.FORMAT_PT_BR_WITH_TIME );

        return  date.contains(filterLowerCase)
                || project.getId().toString().contains(filterLowerCase)
                || project.getPerson().getName().toLowerCase().contains(filterLowerCase)
                || project.getName().toLowerCase().contains(filterLowerCase)
                || project.getRegion().getName().toLowerCase().contains(filterLowerCase);

    }

    private void initCombo() {
        FilteredList<Person> personFilteredList = new FilteredList<>(listPerson(), person -> true);
        comboCustomer.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            personFilteredList.setPredicate(person -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String filterLowerCase = newValue.toLowerCase();

                return person.getName().toLowerCase().contains(filterLowerCase);
            });
        });

        comboCustomer.setItems(personFilteredList);

        /*
        FilteredList<Room> roomFilteredList = new FilteredList<>(listRoom(), room -> true);

        comboRoom.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            roomFilteredList.setPredicate(room -> room.getName().toLowerCase().contains(newValue.toLowerCase()));
        });
        */
        comboRoom.setItems(listRoom());

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

    private ObservableList<Project> listProject() {
        return FXCollections.observableArrayList(projectRepository.findAll());
    }
}
