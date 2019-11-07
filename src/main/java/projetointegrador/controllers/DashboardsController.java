package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projetointegrador.model.*;
import projetointegrador.model.enums.FlowType;
import projetointegrador.model.enums.Rsi;
import projetointegrador.repository.ProjectRepository;
import projetointegrador.repository.RegionRepository;
import projetointegrador.validation.EntityValidator;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

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
    private JFXComboBox<Region> comboRegion;

    @FXML
    private JFXButton btnAddFilter;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private ObservableList<Region> regions;
    private ObservableList data;

    @FXML
    void onAddFilter(ActionEvent event)
    {

    }


    private void initializeFormWizzard()
    {
        initCombo();
        initConverter();
        loadDataPieChart();
    }


    private void initCombo() {
        comboProject.setItems(listProject());
        comboRegion.setItems(listRegion());
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
    }

    public void loadDataPieChart() {
        data = FXCollections.observableArrayList();
        regions = FXCollections.observableArrayList(regionRepository.findAll());

        try {
           regions.forEach(region ->
                data.add(new PieChart.Data(region.getName(), region.getId())) );
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initializeFormWizzard();

        PieChart pieChart = new PieChart();
        pieChart.getData().addAll(data);
        pieChart.setVisible(true);
    }


    private ObservableList<Project> listProject() {
        return FXCollections.observableArrayList(projectRepository.findAll());
    }

    private ObservableList<Region> listRegion() {
        return FXCollections.observableArrayList(regionRepository.findAll());
    }
}
