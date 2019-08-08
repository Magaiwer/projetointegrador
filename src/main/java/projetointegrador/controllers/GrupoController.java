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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
import java.util.Optional;
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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Selecione o grupo que deseja editar");
            alert.show();
        }
    }

    @FXML
    void delete(ActionEvent event) {
        grupo = tableGroup.getSelectionModel().getSelectedItem();

        if (grupo != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Você deseja remover o usuário " + grupo.getNome());
            Optional<ButtonType> confirm = alert.showAndWait();

            if (confirm.get() == ButtonType.OK) {
                grupoRepository.delete(grupo);
                initTable();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Selecione o grupo que deseja deletar");
            alert.show();
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
