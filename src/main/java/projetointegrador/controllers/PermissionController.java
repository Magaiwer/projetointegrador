package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import projetointegrador.Util.MessagesUtil;
import projetointegrador.config.StageManager;
import projetointegrador.model.Group;
import projetointegrador.model.Permission;
import projetointegrador.repository.GroupRepository;
import projetointegrador.repository.PermissionRepository;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PermissionController implements Initializable {

    @FXML
    private JFXComboBox<Group> cbGroups;

    @FXML
    private TableView<Permission> tablePermissions;

    @FXML
    private TableColumn<Permission, String> columnForm;

    @FXML
    private TableColumn<Permission, String> columnPermission;

    @FXML
    private TableColumn<Permission, Boolean> columnEnable;

    @FXML
    private JFXButton btnUpdatePermission;

    CheckBoxTableCell<Permission, Boolean> checkBoxTableCell;

    @FXML
    private JFXTextField txtFilterPermissions;

    @FXML
    private JFXCheckBox cbxUpdateAll;


    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initConverter();
        initCombo();
        initTable();
        initListeners();
    }

    private void initCombo() {
        cbGroups.setItems(listGroup());
    }

    private ObservableList<Group> listGroup() {
        return FXCollections.observableArrayList(groupRepository.findAll());
    }

    private ObservableList<Permission> listPermissionForms() {
        List<Permission> permissions = permissionRepository.findPermissionWithForms();

        if (cbGroups.getSelectionModel().isEmpty()) {
            return FXCollections.observableArrayList(permissions);
        } else {

            List<Permission> permissionsGroup = cbGroups.getSelectionModel().getSelectedItem().getPermissions();

            permissionsGroup
                    .stream()
                    .mapToLong(Permission::getId)
                    .forEach(value -> permissions
                            .stream()
                            .filter(permission -> permission.getId() == value)
                            .forEach(permission -> permission.setHasRole(true)));

            return FXCollections.observableArrayList(permissions);
        }

    }

    private void initConverter() {
        cbGroups.setConverter(new StringConverter<Group>() {
            @Override
            public String toString(Group group) {
                return group.getName();
            }

            @Override
            public Group fromString(String string) {
                return null;
            }
        });
    }


    private void initListeners() {
        cbGroups.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            initTable();
        });

        cbxUpdateAll.selectedProperty().addListener((observable, oldValue, newValue) -> {

            tablePermissions.getItems().forEach(permission -> permission.setHasRole(newValue));
            tablePermissions.refresh();

        });

    }

    private void initTable() {
        columnForm.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getForm().getName()));

        columnPermission.setCellValueFactory(new PropertyValueFactory<>("description"));

        columnEnable.setCellFactory(CheckBoxTableCell.forTableColumn(columnEnable));

        columnEnable.setCellValueFactory(param -> {
            Permission permission = param.getValue();

            BooleanProperty permissionProperty = new SimpleBooleanProperty(permission.isHasRole());
            permissionProperty.addListener((observable, oldValue, newValue) -> permission.setHasRole(newValue));
            return permissionProperty;
        });


        FilteredList<Permission> permissionFilteredList = new FilteredList<>(listPermissionForms(), permission -> true);

        txtFilterPermissions.textProperty().addListener((observable, oldValue, newValue) -> {
            permissionFilteredList.setPredicate(permission -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String filterLowerCase = newValue.toLowerCase();

                if (permission.getForm().getName().toLowerCase().contains(filterLowerCase)) {
                    return true;
                } else if (permission.getDescription().toLowerCase().contains(filterLowerCase)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Permission> permissionSortedList = new SortedList<>(permissionFilteredList);
        permissionSortedList.comparatorProperty().bind(tablePermissions.comparatorProperty());

        tablePermissions.setItems(permissionSortedList);
    }

    @FXML
    void updatePermission(ActionEvent event) {
        if (!cbGroups.getSelectionModel().isEmpty()) {

            txtFilterPermissions.clear();

            List<Permission> permissionList = tablePermissions.getItems().filtered(Permission::isHasRole);

            Group group = cbGroups.getSelectionModel().getSelectedItem();
            group.setPermissions(permissionList);

            try {
                groupRepository.saveAndFlush(group);
                MessagesUtil.showMessageInformation("Permissões atualizadas com sucesso!");
                txtFilterPermissions.clear();

            } catch (Exception e) {
                MessagesUtil.showMessageError("Erro ao aplicar permissões, tente novamente!");
            }
        } else {
            MessagesUtil.showMessageWarning("Selecione um grupo para aplicar as permissões");
        }
    }

}

