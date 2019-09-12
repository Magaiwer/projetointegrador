package projetointegrador.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import projetointegrador.config.StageManager;
import projetointegrador.model.Form;
import projetointegrador.model.Grupo;
import projetointegrador.model.Permission;
import projetointegrador.repository.FormRepository;
import projetointegrador.repository.GrupoRepository;
import projetointegrador.repository.PermissionRepository;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class PermissionController implements Initializable {

    @FXML
    private JFXComboBox<Grupo> cbGroups;


    @FXML
    private TableView<Permission> tablePermissions;

    @FXML
    private TableColumn<Permission, String> columnForm;

    @FXML
    private TableColumn<Permission, String> columnPermission;

    @FXML
    private TableColumn<Boolean, Boolean> columnEnable;
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initConverter();
        initCombo();
        initTable();
    }

    private void initCombo() {
        cbGroups.setItems(listGroup());
    }

    private ObservableList<Grupo> listGroup() {
        return FXCollections.observableArrayList(grupoRepository.findAll());
    }

    private ObservableList<Permission> listForms() {
        return FXCollections.observableArrayList(permissionRepository.findPermissionWithForms());
    }

    private void initConverter() {
        cbGroups.setConverter(new StringConverter<Grupo>() {
            @Override
            public String toString(Grupo grupo) {
                return grupo.getNome();
            }

            @Override
            public Grupo fromString(String string) {
                return null;
            }
        });
    }

    private void initTable() {
        columnForm.setCellValueFactory(param -> {
            return new SimpleStringProperty(param.getValue().getForm().getName());
        });

        columnPermission.setCellValueFactory(new PropertyValueFactory<>("description"));

 /*       columnEnable.setCellValueFactory(param -> {
            BooleanProperty auditProperty = new SimpleBooleanProperty(form.isAudit());
            auditProperty.addListener((observable, oldValue, newValue) -> form.setAudit(newValue));
            return auditProperty;
        });*/



        tablePermissions.setItems(listForms());

    }


    private static final class PermissionObjectTree extends RecursiveTreeObject<PermissionObjectTree> {


        @Getter
        private Permission permission;

        @Getter
        private Form form;

        public PermissionObjectTree(Permission permission) {
            this.permission = permission;
        }
        public PermissionObjectTree(Form form) {
            this.form = form;
        }
    }

}

