package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import projetointegrador.Util.EFxmlView;
import projetointegrador.config.StageManager;
import projetointegrador.model.Grupo;
import projetointegrador.model.Grupo;
import projetointegrador.model.Usuario;
import projetointegrador.repository.GrupoRepository;
import projetointegrador.repository.GrupoRepository;

import java.net.URL;
import java.security.Guard;
import java.util.ResourceBundle;

@Component
public class GrupoController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXTextField txtName;

    @FXML
    private TableView<Grupo> tableGroup;


    @FXML
    private TableColumn<Grupo, Long> colunmId;

    @FXML
    private TableColumn<Grupo, String> colunmName;


    @Autowired
    private GrupoRepository grupoRepository;

    @Lazy
    @Autowired
    private StageManager stageManager;

    private Grupo grupo = new Grupo();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //grupo = new Grupo();
        initTable();
    }

    @FXML
    void save(ActionEvent event) {
        if (grupo != null) {
            grupo.setNome(txtName.getText());

            grupoRepository.save(grupo);
            stageManager.switchScene(root, EFxmlView.GROUP_TABLE);
        }
        grupo = new Grupo();

    }

    @FXML
    void cancel(ActionEvent event) {
        stageManager.switchScene(root, EFxmlView.GROUP_TABLE);
    }

    @FXML
    void newGroup(ActionEvent event) {
        stageManager.switchScene(root, EFxmlView.GROUP);
        txtName.requestFocus();
    }

    @FXML
    void edit(ActionEvent event) {
        grupo = tableGroup.getSelectionModel().getSelectedItem();

        if (grupo != null) {
            stageManager.switchScene(root, EFxmlView.GROUP);
            txtName.setText(grupo.getNome());
        } else {
            // TODO
            System.out.println("SELECIONE UM GRUPO");
        }
    }

    @FXML
    void delete(ActionEvent event) {
        grupo = tableGroup.getSelectionModel().getSelectedItem();

        if (grupo != null) {
            grupoRepository.delete(grupo);
            initTable();
        } else {
            // TODO
            System.out.println("SELECIONE UM GRUPO");
        }

    }

    private ObservableList<Grupo> listGroup() {
        return FXCollections.observableArrayList(grupoRepository.findAll());
    }

    private void initTable() {
        colunmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunmName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableGroup.setItems(listGroup());
    }

}
