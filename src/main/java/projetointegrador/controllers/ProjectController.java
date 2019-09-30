package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projetointegrador.model.Material;
import projetointegrador.model.Person;
import projetointegrador.model.Room;
import projetointegrador.repository.PersonRepository;
import projetointegrador.repository.RoomRepository;

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
    private JFXComboBox<Person> comboCustumer;

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
    private JFXComboBox<Material> comboMaterial;

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

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoomRepository roomRepository;

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

    }

    @FXML
    void onNext(ActionEvent event) {
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
}
