package projetointegrador.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
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
    private JFXTreeTableView<PermissionObjectTree> tablePermissions;

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

        TreeTableColumn<PermissionObjectTree, String> formColumn = new JFXTreeTableColumn<>("Formularios");
        formColumn.setPrefWidth(150);

        formColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<PermissionObjectTree, String> param) -> {
            TreeItem<PermissionObjectTree> form = param.getValue();
            if (form == null) {
                return new SimpleStringProperty();
            }
            return new SimpleStringProperty(form.getValue().permission.getForm().getName());
        });

        tablePermissions.getColumns().add(formColumn);


        TreeItem<PermissionObjectTree> rootItem = new TreeItem<>();
        rootItem.setExpanded(true);


        listForms().forEach(permission -> {

            TreeItem<PermissionObjectTree> formItem = new TreeItem<>(new PermissionObjectTree(permission));
            TreeItem<PermissionObjectTree> permissionItem = new TreeItem<>(new PermissionObjectTree(permission.getForm()));
            formItem.getChildren().add(permissionItem);
/*            form.getPermissions().forEach(permission -> {


            });*/

            System.out.println(formItem);
            rootItem.getChildren().add(formItem);


        });



        tablePermissions.setShowRoot(false);
        tablePermissions.setRoot(rootItem);
        tablePermissions.setPrefHeight(Short.MAX_VALUE);










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

