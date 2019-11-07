package projetointegrador.controllers;

import com.jfoenix.controls.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import projetointegrador.Util.EFxmlView;
import projetointegrador.Util.Formatter;
import projetointegrador.Util.MessagesUtil;
import projetointegrador.config.StageManager;
import projetointegrador.model.*;
import projetointegrador.model.enums.FlowType;
import projetointegrador.model.enums.Rsi;
import projetointegrador.repository.*;
import projetointegrador.service.ComponentService;
import projetointegrador.service.FaceService;
import projetointegrador.service.ProjectService;
import projetointegrador.service.RoomService;
import projetointegrador.validation.EntityValidator;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

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
    private TableView<Component> tableComponent;

    @FXML
    private TableColumn<Component, String> columnRoomComponent;

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
    private JFXTextField txtThermalConductivity;

    @FXML
    private JFXTextField txtFaceComponentMaterial;

    @FXML
    private JFXTextField txtTransmittance;

    @FXML
    private JFXTextField txtSolarFactor;

    @FXML
    private JFXTextField txtTransmittanceComponent;

    @FXML
    private JFXTextField txtm2;

    @FXML
    private JFXTextArea txtArea;

    @FXML
    private TableView<ComponentMaterial> tableComponentMaterial;

    @FXML
    private TableColumn<ComponentMaterial, String> columnComponentMaterial;

    @FXML
    private TableColumn<ComponentMaterial, String> columnFaceComponentMaterial;

    @FXML
    private TableColumn<ComponentMaterial, String> columnMaterialComponent;

    @FXML
    private TableColumn<ComponentMaterial, BigDecimal> columnThicknessComponent;
    @FXML
    private TableColumn<ComponentMaterial, BigDecimal> columnResistanceComponent;

    @FXML
    private JFXButton btnAddComponentMaterial;

    @FXML
    private Tab tabCalculate;

    @FXML
    private JFXTextField txtTemperatureOutside;

    @FXML
    private JFXTextField txtTemperatureInside;

    @FXML
    private JFXComboBox<MaterialAbsortancia> comboAbsorbance;

    @FXML
    private JFXComboBox<Component> comboComponentCalculate;

    @FXML
    private JFXComboBox<Rsi> comboRSI;

    @FXML
    private JFXRadioButton rgWinter;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private JFXRadioButton rgSummer;

    @FXML
    private JFXTextField txtAlpha;

    @FXML
    private JFXTextField txtBTUS;

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
    private TableView<Component> tableCalculate;

    @FXML
    private TableColumn<Component, String> columnFaceCalculate;

    @FXML
    private TableColumn<Component, BigDecimal> columnThermalLoad;

    @FXML
    private TableColumn<Component, String> columnComponentCalculate;

    @FXML
    private TableColumn<Component, BigDecimal> columnTransmittanceCalculate;

    @FXML
    private TableColumn<Component, BigDecimal> columnQfoCalculate;

    @FXML
    private TableColumn<Component, BigDecimal> columnQftCalculate;

    @FXML
    private TableColumn<Component, String> columnRoomCalculate;

    @FXML
    private JFXButton btnNewProject;

    @FXML
    private JFXButton btnEditProject;

    @FXML
    private JFXButton btnDeleteProject;

    @FXML
    private JFXButton btnDetailProject;

    @FXML
    private JFXButton btnCalculate;

    @FXML
    private JFXButton btnEmail;

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
    private ComponentRepository componentRepository;

    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private MaterialAbsortanciaRepository absorbanceRpository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private FaceService faceService;

    @Autowired
    private ComponentService componentService;

    @Lazy
    @Autowired
    private StageManager stageManager;

    private Project project = new Project();
    private Room room;
    private Face face;
    private Component component;

    private List<Room> listRoom;
    private List<Face> listFace;
    private List<Component> listComponent;
    private Set<Component> componentSet;
    private List<ComponentMaterial> componentMaterials;

    private void initializeFormWizzard() {
        if (txtIndex != null) {
            room = new Room();
            face = new Face();
            component = new Component();

            listRoom = new ArrayList<>();
            listFace = new ArrayList<>();
            listComponent = new ArrayList<>();
            componentSet = new HashSet<>();
            componentMaterials = new ArrayList<>();

            txtTemperatureOutside.setText("30");
            txtTemperatureInside.setText("25");

            rgSummer.setUserData(FlowType.SUMMER);
            rgWinter.setUserData(FlowType.WINTER);

            initCombo();
            initTableRoom();
            initTableFace();
            initTableComponent();
            initTableComponentMaterial();
            initTableCalculate();
            initConverter();
            initListeners();

            EntityValidator.noEmpty(txtNameProject, txtIndex, txtNameRoom, txtNameFace, txtNameComponent, txtAlpha,
                    txtThickness, txtTemperatureInside, txtTemperatureOutside, txtm2
            );

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
    void onAddRoom(ActionEvent event) {
        boolean noEmpty = EntityValidator.noEmpty(txtNameRoom);
        if (room != null && noEmpty) {
            bindRoom();
            listRoom.add(room);
            tableRoom.setItems(FXCollections.observableArrayList(listRoom));
            EntityValidator.clearFields(txtNameRoom);
        } else {
            MessagesUtil.showMessageError("Verifique o preechimento dos campos obrigátorios");
        }
    }

    @FXML
    void onAddFace(ActionEvent event) {
        boolean noEmpty = EntityValidator.noEmpty(txtNameFace);
        if (face != null && noEmpty) {
            bindFace();
            listFace.add(face);
            tableFace.setItems(FXCollections.observableArrayList(listFace));
            EntityValidator.clearFields(txtNameFace);
        } else {
            MessagesUtil.showMessageError("Verifique o preechimento dos campos obrigátorios");
        }
    }

    @FXML
    void onAddComponent(ActionEvent event) {
        boolean noEmpty = EntityValidator.noEmpty(txtNameComponent);
        bindComponent();
        if (component != null && noEmpty) {

            tableComponent.getItems().add(component);
            EntityValidator.clearFields(txtNameComponent);
        } else {
            MessagesUtil.showMessageError("Verifique o preechimento dos campos obrigátorios");
        }
    }

    @FXML
    void onAddComponentMaterial(ActionEvent event) {
        boolean noEmpty = EntityValidator.noEmpty(txtThickness);

        bindComponentMaterial();
        if (component != null && noEmpty) {

            componentService.calculateTransmittance(component);

            txtTransmittanceComponent.setText(component.getTransmittance().toString());
            txtTransmittanceComponent.setVisible(true);

            tableComponentMaterial.setItems(FXCollections.observableArrayList(component.getComponentMaterials()));
            addComboComponentCalculate(component);

            EntityValidator.clearFields(txtThickness);
        } else {
            MessagesUtil.showMessageError("Verifique o preechimento dos campos obrigátorios");
        }
    }

    @FXML
    void onCalculate(ActionEvent event) {

        boolean noEmpty = EntityValidator.noEmpty(txtAlpha, txtm2, txtTemperatureOutside, txtTemperatureInside);
        component = comboComponentCalculate.getSelectionModel().getSelectedItem();

        if (component != null && noEmpty) {
            bindCalculate(component);
            componentService.calculateTransmittance(component);
            componentService.calculateQFO(component);

            component.getFace().addComponent(component);
            component.getFace().calculateThermalLoad();


            tableCalculate.getItems().removeIf(component1 ->
                    (component1.getId().equals(component.getId())
                            && (component1.getFlowType().equals(component.getFlowType()))));

            updateBTUS(component);
            tableCalculate.getItems().add(component);
            tableCalculate.refresh();

        } else {
            MessagesUtil.showMessageError("Verifique o preechimento dos campos obrigátorios");
        }

    }

    private void updateBTUS(Component component) {
        txtBTUS.setText(component.getFace().calculateBtus().toString());
        txtBTUS.setVisible(true);
    }

    @FXML
    @Override
    public void onSave(ActionEvent event) {
        if (tabProject.isSelected()) {
            projectSave();
        }
        if (tabRoom.isSelected()) {
            roomSave();
        }
        if (tabFace.isSelected()) {
            faceSave();
        }
        if (tabComponent.isSelected()) {
            componentSave();
        }
        if (tabComponentMaterial.isSelected()) {
            componentMaterialSave();
        }
        if (tabCalculate.isSelected()) {
            finalSave();
        }
    }

    private void finalSave() {
        listComponent = tableCalculate.getItems();
        if (!listComponent.isEmpty()) {
            try {
                listComponent.forEach(component1 -> faceService.save(component1.getFace()));
                componentService.saveAll(listComponent);

                MessagesUtil.showMessageInformation(" salvo(s) com sucesso");
            } catch (Exception e) {
                MessagesUtil.showMessageError(e.getMessage());
            }
        }
    }

    private void componentMaterialSave() {
        listComponent = comboComponentCalculate.getItems();

        if (!listComponent.isEmpty()) {
            try {

                componentService.saveAll(listComponent);
                MessagesUtil.showMessageInformation("Conjunto de materiais do(s) componente(s) da face foram salvo(s) com sucesso");
            } catch (Exception e) {
                MessagesUtil.showMessageError(e.getMessage());
            }
        }
    }

    private void componentSave() {
        if (component != null) {
            try {
                List<Component> componentList = componentService.saveAll(tableComponent.getItems());
                comboComponent.setItems(FXCollections.observableArrayList(componentList));

                MessagesUtil.showMessageInformation("Componente(s) da face salvo(s) com sucesso");
            } catch (Exception e) {
                MessagesUtil.showMessageError(e.getMessage());
            }
        }
    }

    private void faceSave() {
        if (face != null) {
            try {
                List<Face> faceList = faceService.saveAll(tableFace.getItems());

                comboFace.setItems(FXCollections.observableArrayList(faceList));

                MessagesUtil.showMessageInformation("Face(s) salva(s) com sucesso");
            } catch (Exception e) {
                MessagesUtil.showMessageError(e.getMessage());
            }
        }
    }

    private void roomSave() {
        if (room != null) {
            try {
                List<Room> roomList = roomService.saveAll(tableRoom.getItems());

                comboRoom.setItems(FXCollections.observableArrayList(roomList));

                MessagesUtil.showMessageInformation("Comodo(s) salvo(s) com sucesso");
            } catch (Exception e) {
                MessagesUtil.showMessageError(e.getMessage());
            }
        }
    }

    private void projectSave() {
        boolean noEmpty = EntityValidator.noEmpty(txtNameProject, txtIndex);

        bindProject();
        if (project != null && noEmpty) {

            try {
                projectService.save(project);
                MessagesUtil.showMessageInformation("Projeto salvo com sucesso");

            } catch (Exception e) {
                MessagesUtil.showMessageError(e.getMessage());
            }
        }
    }

    private void bindProject() {
        project = new Project();
        project.setName(txtNameProject.getText());
        project.setDescription(txtDescription.getText());
        project.setPerson(comboCustomer.getValue());
        project.setRegion(comboRegion.getValue());
    }

    private void bindRoom() {
        room = new Room();
        room.setProject(project);
        room.setName(txtNameRoom.getText());
    }

    private void bindFace() {
        face = new Face();
        face.setName(txtNameFace.getText());
        face.setRoom(comboRoom.getSelectionModel().getSelectedItem());
    }

    private void bindComponent() {
        component = new Component();
        component.setName(txtNameComponent.getText());
        component.setFace(comboFace.getSelectionModel().getSelectedItem());
        component.setIndexRadiation(new BigDecimal(txtIndex.getText()));
    }

    private void bindComponentMaterial() {
        component = comboComponent.getSelectionModel().getSelectedItem();
        component.addMaterial(comboMaterial.getSelectionModel().getSelectedItem(), new BigDecimal(txtThickness.getText()));
        component.setRsi(comboRSI.getSelectionModel().getSelectedItem().getValue());
    }

    private void bindCalculate(Component component) {
        if(component != null) {
            component.setAlpha(new BigDecimal(txtAlpha.getText()));
            component.setM2(new BigDecimal(txtm2.getText()));
            component.setTemperatureOutside(new BigDecimal(txtTemperatureOutside.getText()));
            component.setTemperatureInside(new BigDecimal(txtTemperatureOutside.getText()));
            String flowType = toggleGroup.getSelectedToggle().getUserData().toString();
            component.setFlowType(FlowType.valueOf(flowType));
        }
    }

    @FXML
    @Override
    public void onEdit(ActionEvent event) {
        project = tableProject.getSelectionModel().getSelectedItem();

        if (project != null) {
            stageManager.switchScene(root, EFxmlView.PROJECT);
            Optional<Project> projectOptional = projectRepository.findById(project.getId());

            projectOptional.ifPresent(p -> project = p);

            txtNameProject.setText(project.getName());
            txtDescription.setText(project.getDescription());
            comboCustomer.setValue(project.getPerson());
            comboRegion.setValue(project.getRegion());

            listRoom = roomRepository.findByProjectWithFaces(project.getId());
            tableRoom.setItems(FXCollections.observableArrayList(listRoom));


            listRoom.forEach(room1 -> listFace.addAll(room1.getFaces()));
            tableFace.setItems(FXCollections.observableArrayList(listFace));

            listFace.forEach(face1 -> listComponent = componentRepository.findByFace(face1.getId()));
            tableComponent.setItems(FXCollections.observableArrayList(listComponent));

            listComponent.forEach(component1 -> componentMaterials.addAll(component1.getComponentMaterials()));
            tableComponentMaterial.setItems(FXCollections.observableArrayList(componentMaterials));


        } else {
            MessagesUtil.showMessageWarning("Selecione um Projeto");
        }
    }

    @FXML
    public void onShowDetail(ActionEvent event) throws IOException {

        project = tableProject.getSelectionModel().getSelectedItem();

        if (project != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(EFxmlView.PROJECT_DETAIL.getFxmlFile()));

            DetailController detailController = new DetailController(project);
            loader.setController(detailController);

            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setMaximized(false);
            stage.show();
        } else {
            MessagesUtil.showMessageWarning("Selecione um Projeto");
        }

    }

    @FXML
    public void onShowMailSender(ActionEvent event) throws IOException {
        project = tableProject.getSelectionModel().getSelectedItem();

        if (project != null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(EFxmlView.EMAIL.getFxmlFile()));

            EmailController emailController = new EmailController(project);
            loader.setController(emailController);

            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));

            emailController.setStage(stage);
            stage.show();
        } else {
            MessagesUtil.showMessageWarning("Selecione um Projeto");
        }
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
        comboRegion.valueProperty().addListener((observable, oldValue, newValue) -> txtIndex.setText(newValue.getIndex().toString()));

        comboMaterial.valueProperty().addListener((observable, oldValue, newValue) -> {
            txtThermalConductivity.setText(newValue.getCondutividadeTermica().toString());
            txtTransmittance.setVisible(newValue.isGlass());
            txtSolarFactor.setVisible(newValue.isGlass());
        });

        comboAbsorbance.valueProperty().addListener((observable, oldValue, newValue) -> txtAlpha.setText(newValue.getAlfaIni().toString()));
        comboComponent.valueProperty().addListener((observable, oldValue, newValue) -> txtFaceComponentMaterial.setText(newValue.getFace().getName()));

    }

    private void initTableCalculate() {
        columnRoomCalculate.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFace().getRoom().getName()));
        columnFaceCalculate.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFace().getName()));
        columnThermalLoad.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getFace().getThermalLoad()));
        columnComponentCalculate.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnTransmittanceCalculate.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getTransmittance()));
        columnQfoCalculate.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getQfo()));
        columnQftCalculate.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getQft()));
    }

    private void initTableRoom() {
        columnNameRoom.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void initTableFace() {

        columnFace.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnFaceRoom.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getRoom().getName()));
    }

    private void initTableComponent() {
        columnRoomComponent.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFace().getRoom().getName()));
        columnNameComponent.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnNameFace.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFace().getName()));
    }

    private void initTableComponentMaterial() {
        columnFaceComponentMaterial.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getComponent().getFace().getName()));
        columnComponentMaterial.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getComponent().getName()));
        columnMaterialComponent.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getMaterial().getNome()));
        columnThicknessComponent.setCellValueFactory(new PropertyValueFactory<>("thickness"));
        columnResistanceComponent.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getResistance()));
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

        txtFilterProject.textProperty().addListener((observable, oldValue, newValue) -> projectFilteredList.setPredicate(project ->
        {
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
        String date = Formatter.getDateTextFormated(project.getDate(), Formatter.FORMAT_PT_BR_WITH_TIME);

        return date.contains(filterLowerCase)
                || project.getId().toString().contains(filterLowerCase)
                || project.getPerson().getName().toLowerCase().contains(filterLowerCase)
                || project.getName().toLowerCase().contains(filterLowerCase)
                || project.getRegion().getName().toLowerCase().contains(filterLowerCase);

    }

    private void initCombo() {
        FilteredList<Person> personFilteredList = new FilteredList<>(listPerson(), person -> true);
        comboCustomer.getEditor().textProperty().addListener((observable, oldValue, newValue) ->
        {
            personFilteredList.setPredicate(person ->
            {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String filterLowerCase = newValue.toLowerCase();

                return person.getName().toLowerCase().contains(filterLowerCase);
            });
        });

        comboCustomer.setItems(personFilteredList);
        comboRegion.setItems(listRegion());
        comboMaterial.setItems(listMaterial());
        comboAbsorbance.setItems(listMaterialAbsorbance());

        comboRSI.setItems(FXCollections.observableArrayList(Rsi.values()));
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

        comboCustomer.setConverter(new StringConverter<Person>() {
            @Override
            public String toString(Person person) {
                return person.getName();
            }

            @Override
            public Person fromString(String string) {
                return null;
            }
        });

        comboRegion.setConverter(new StringConverter<Region>() {
            @Override
            public String toString(Region region) {
                return region.getName();
            }

            @Override
            public Region fromString(String string) {
                return null;
            }
        });

        comboAbsorbance.setConverter(new StringConverter<MaterialAbsortancia>() {
            @Override
            public String toString(MaterialAbsortancia object) {
                return object.getSuperficie();
            }

            @Override
            public MaterialAbsortancia fromString(String string) {
                return null;
            }
        });

        StringConverter<Component> componentStringConverter = new StringConverter<Component>() {
            @Override
            public String toString(Component component) {
                return component.getName();
            }

            @Override
            public Component fromString(String string) {
                return null;
            }
        };

        comboComponent.setConverter(componentStringConverter);
        comboComponentCalculate.setConverter(componentStringConverter);

        comboMaterial.setConverter(new StringConverter<Material>() {
            @Override
            public String toString(Material object) {
                return object.getNome();
            }

            @Override
            public Material fromString(String string) {
                return null;
            }
        });

        comboRSI.setConverter(new StringConverter<Rsi>() {
            @Override
            public String toString(Rsi object) {
                return object.getDescription();
            }

            @Override
            public Rsi fromString(String string) {
                return null;
            }
        });

    }

    @FXML
    void onNext(ActionEvent event) {
        this.onSave(event);
        tabPane.getSelectionModel().selectNext();

        if (tabCalculate.isSelected()) {
            btnNextProject.setText("Salvar");
        }
    }

    @FXML
    void onPrevious(ActionEvent event) {
        btnNextProject.setText("Proxímo");
        tabPane.getSelectionModel().selectPrevious();
    }

    private ObservableList<Person> listPerson() {
        return FXCollections.observableArrayList(personRepository.findAll());
    }

    private ObservableList<Region> listRegion() {
        return FXCollections.observableArrayList(regionRepository.findAll());
    }

    private ObservableList<Project> listProject() {
        return FXCollections.observableArrayList(projectRepository.findAll());
    }

    private ObservableList<Material> listMaterial() {
        return FXCollections.observableArrayList(materialRepository.findAll());
    }

    private ObservableList<MaterialAbsortancia> listMaterialAbsorbance() {
        return FXCollections.observableArrayList(absorbanceRpository.findAll());
    }

    private void addListComponents(Component component) {
        this.listComponent.clear();
        this.listComponent.add(component);
    }

    private void addComboComponentCalculate(Component component) {
        comboComponentCalculate.getItems().removeIf(component1 -> component1.getId().equals(component.getId()));
        comboComponentCalculate.getItems().add(component);
    }
}
