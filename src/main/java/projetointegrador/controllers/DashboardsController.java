package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projetointegrador.model.*;
import projetointegrador.model.enums.FlowType;
import projetointegrador.model.enums.Rsi;
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
    private AnchorPane root;

    @FXML
    private JFXComboBox<Project> comboProject;

    @FXML
    private JFXComboBox<Region> comboRegion;

    @FXML
    private JFXButton btnAddFilter;

    @Autowired
    private RegionRepository regionRepository;

    @FXML
    void onAddFilter(ActionEvent event)
    {

    }


    private void initializeFormWizzard()
    {
        initCombo();
        initConverter();
    }


    private void initCombo() {
        comboRegion.setItems(listRegion());
    }


    private void initConverter() {
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

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initializeFormWizzard();
    }

    private ObservableList<Region> listRegion() {
        return FXCollections.observableArrayList(regionRepository.findAll());
    }
}
