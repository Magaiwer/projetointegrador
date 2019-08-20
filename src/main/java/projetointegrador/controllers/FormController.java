package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import projetointegrador.Util.EFxmlView;
import projetointegrador.config.StageManager;
import projetointegrador.model.Entities;
import projetointegrador.model.Form;
import projetointegrador.repository.FormRepository;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class FormController implements Initializable {


    @FXML
    private AnchorPane panel;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXCheckBox cbAudit;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Form> tableForm;

    @FXML
    private TableColumn<Form, Long> colunmId;

    @FXML
    private TableColumn<Form, String> colunmName;

    @FXML
    private TableColumn<Form, String> colunmDescription;

    @FXML
    private TableColumn<Form, Boolean> colunmAudit;

    @FXML
    private JFXCheckBox tableCBAudit;

    @FXML
    private TableColumn<Form, Entities> colunmEntity;

    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnDelete;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private FormRepository formRepository;

    private Form form = new Form();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    @FXML
    void cancel(ActionEvent event) {
        stageManager.switchScene(panel, EFxmlView.FORM_TABLE);
    }

    @FXML
    void save(ActionEvent event) {
        if (form != null) {

            form.setName(txtName.getText());
            form.setAudit(cbAudit.isSelected());
            form.setDescription(txtDescription.getText());

            formRepository.save(form);
            stageManager.switchScene(panel, EFxmlView.FORM_TABLE);
        }
        form = new Form();
    }

    @FXML
    void onDelete(ActionEvent event) {

    }

    @FXML
    void onEdit(ActionEvent event) {
        form = tableForm.getSelectionModel().getSelectedItem();

        if (form != null) {
            stageManager.switchScene(root, EFxmlView.FORM);
            txtName.setText(form.getName());
            txtDescription.setText(form.getDescription());
            cbAudit.setSelected(form.isAudit());


        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Selecione o form que deseja editar");
            alert.show();
        }
    }

    @FXML
    void newForm(ActionEvent event) {
        stageManager.switchScene(root, EFxmlView.FORM);
        txtName.requestFocus();
    }


    private ObservableList<Form> listGroup() {
        return FXCollections.observableArrayList(formRepository.findAll());
    }
    private void initTable() {
        colunmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colunmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colunmAudit.setCellValueFactory(new PropertyValueFactory<>("audited"));
        tableForm.setItems(listGroup());
    }
}
