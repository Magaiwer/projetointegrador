package projetointegrador.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
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
import projetointegrador.repository.FormRepository;
import projetointegrador.repository.GrupoRepository;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class PermissionController implements Initializable {

    @FXML
    private JFXComboBox<Grupo> cbGroups;

    @FXML
    private JFXTreeTableView<FormObjectTree> tablePermissions;

    @Autowired
    private FormRepository formRepository;

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

    private ObservableList<Form> listForms() {
        return FXCollections.observableArrayList(formRepository.findFormWithPermissions());
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

        TreeTableColumn<FormObjectTree, String> formColumn = new JFXTreeTableColumn<>("Formularios");
        formColumn.setPrefWidth(150);

        formColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<FormObjectTree, String> param) -> {
            TreeItem<FormObjectTree> form = param.getValue();
            if (form == null) {
                return new SimpleStringProperty();
            }
            return new SimpleStringProperty(form.getValue().form.getName());
        });


        tablePermissions.getColumns().add(formColumn);
        TreeItem treeItem = new TreeItem();
        treeItem.setExpanded(true);
        tablePermissions.setShowRoot(false);
        tablePermissions.setRoot(treeItem);
        tablePermissions.setPrefHeight(Short.MAX_VALUE);

      listForms().forEach(form -> {
                    tablePermissions.getRoot().getChildren().add(new TreeItem<>(new FormObjectTree(form)));
                   /* tablePermissions.getRoot().getChildren().add(new TreeItem<Permission>(new FormObjectTree(form).getForm().getPermissions()));*/
                });
    }


    @AllArgsConstructor
    private static final class FormObjectTree extends RecursiveTreeObject<FormObjectTree> {
        @Getter
        private Form form;
    }

}

