package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projetointegrador.model.*;
import projetointegrador.model.enums.FlowType;
import projetointegrador.model.enums.Rsi;
import projetointegrador.repository.PersonRepository;
import projetointegrador.repository.ProjectRepository;
import projetointegrador.repository.RegionRepository;
import projetointegrador.repository.RoomRepository;
import projetointegrador.validation.EntityValidator;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DashboardsController implements Initializable
{
    @FXML
    PieChart piechart;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXComboBox<Project> comboProject;

    @FXML
    private JFXButton btnAddFilter;

    @FXML
    private JFXButton btnClearFilters;

    @FXML
    private JFXButton btnTotalProjects;

    @FXML
    private JFXButton btnTotalClients;

    @FXML
    private JFXButton btnAverageRooms;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoomRepository roomRepository;

    @FXML
    private TableView<Region> tableProjectsByRegion;

    @FXML
    private TableColumn<Region, String> columnRegion;

    @FXML
    private TableColumn<Region, Long> columnAmountProjects;

    @FXML
    private PieChart pieChart;
    @FXML
    private PieChart pieChartRegions;


    private ObservableList<Region> regions;
    private ObservableList<Person> persons;

    @FXML
    void onApplyFilters(ActionEvent event)
    {
        loadTotalProjects();
        loadTotalClients();
        loadAverageRoomsByProject();
        loadDataPieChart();
        loadDataPieChartRegions();
    }

    @FXML
    void onClearFilters(ActionEvent event)
    {
        comboProject.getItems().clear();
        initCombo();
    }

    private void initializeFormWizzard()
    {
        initCombo();
        initConverter();
        loadDataPieChart();
        loadDataPieChartRegions();
        loadTotalProjects();
        loadTotalClients();
        loadAverageRoomsByProject();
    }

    private ObservableList<Region> listRegions() {
        return FXCollections.observableArrayList(regionRepository.findAll());
    }

    private void initCombo() {
        comboProject.setItems(listProject());
    }

    private void initConverter() {
        comboProject.setConverter(new StringConverter<Project>() {
            @Override
            public String toString(Project project) {
                return project.getName();
            }

            @Override
            public Project fromString(String string) {
                return null;
            }
        });
    }

    public void loadDataPieChart()
    {
        persons = FXCollections.observableArrayList(personRepository.findAll());
        
        PieChart.Data data[] = new PieChart.Data[persons.size()];

        AtomicInteger atomicInteger = new AtomicInteger(0);
        persons.forEach(person ->
            data[atomicInteger.getAndIncrement()] = new PieChart.Data(person.getName(), personRepository.findProjectsByPerson(person.getId()).size()));

        pieChart.setTitle("Quantidade de projetos por cliente");
        pieChart.setData(FXCollections.observableArrayList(data));
    }

    public void loadDataPieChartRegions()
    {
        regions = FXCollections.observableArrayList(regionRepository.findAll());

        PieChart.Data data[] = new PieChart.Data[regions.size()];

        AtomicInteger atomicInteger = new AtomicInteger(0);
        regions.forEach(region ->
                data[atomicInteger.getAndIncrement()] = new PieChart.Data(region.getName(), projectRepository.findByRegionId(region.getId()).size()));

        pieChartRegions.setTitle("Quantidade de projetos por regiao");
        pieChartRegions.setData(FXCollections.observableArrayList(data));
    }

    public void loadTotalProjects()
    {
        if(comboProject.getValue() != null)
        {
            Project selectedProject = (Project) comboProject.getSelectionModel().getSelectedItem();
            Long id = selectedProject.getId();

            btnTotalProjects.setText(String.valueOf(projectRepository.findProjectById(id).size()));
        }
        else
        {
            btnTotalProjects.setText(String.valueOf(projectRepository.findAll().size()));
        }
    }

    public void loadTotalClients()
    {
        if(comboProject.getValue() != null)
        {
            btnTotalClients.setText(String.valueOf(1));
        }
        else
        {
            btnTotalClients.setText(String.valueOf(personRepository.findAll().size()));
        }
    }

    public void loadAverageRoomsByProject()
    {
        if(comboProject.getValue() != null)
        {
            Project selectedProject = (Project) comboProject.getSelectionModel().getSelectedItem();
            Long id = selectedProject.getId();
            System.out.println(roomRepository.findByProjectId(id));
            btnAverageRooms.setText(String.valueOf(roomRepository.findByProjectId(id).size()));
        }
        else
        {
            btnAverageRooms.setText(BigDecimal.valueOf(roomRepository.findAll()
                    .stream()
                    .map(Room::getId)
                    .mapToDouble(Long::doubleValue)
                    .average().orElse(BigDecimal.ZERO.doubleValue())).toString());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initializeFormWizzard();
    }

    private ObservableList<Project> listProject()
    {
        return FXCollections.observableArrayList(projectRepository.findAll());
    }
}
