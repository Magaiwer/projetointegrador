package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import projetointegrador.Util.EFxmlView;
import projetointegrador.Util.MessagesUtil;
import projetointegrador.config.StageManager;
import projetointegrador.model.Group;
import projetointegrador.repository.GroupRepository;
import projetointegrador.validation.EntityValidator;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class GrupoController implements Initializable, BaseController<GrupoController> {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnSaveGroup;

    @FXML
    private JFXTextField txtName;

    @FXML
    private TableView<Group> tableGroup;


    @FXML
    private TableColumn<Group, Long> colunmId;

    @FXML
    private TableColumn<Group, String> colunmName;

    @FXML
    private JFXButton btnEditGroup;

    @FXML
    private JFXButton btnNewGroup;

    @FXML
    private JFXButton btnDeleteGroup;


    @Autowired
    private GroupRepository groupRepository;

    @Lazy
    @Autowired
    private StageManager stageManager;

    private static Logger LOGGER = LoggerFactory.getLogger(Group.class);

    private Group group = new Group();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();

        if(txtName != null) {
            EntityValidator.noEmpty(txtName);
        }
    }


    @FXML
    @Override
    public void onCancel(ActionEvent event) {
        stageManager.switchScene(root, EFxmlView.GROUP_TABLE);
    }

    @FXML
    @Override
    public void onSave(ActionEvent event) {
        boolean noEmpty = EntityValidator.noEmpty(txtName);

        if (group != null && noEmpty) {
            group.setName(txtName.getText());
            try {
                groupRepository.save(group);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }

            stageManager.switchScene(root, EFxmlView.GROUP_TABLE);
        }
        group = new Group();
    }

    @FXML
    @Override
    public void onEdit(ActionEvent event) {
        group = tableGroup.getSelectionModel().getSelectedItem();

        if (group != null) {
            stageManager.switchScene(root, EFxmlView.GROUP);
            txtName.setText(group.getName());
        } else {
            MessagesUtil.showMessageWarning("Selecione o group que deseja editar");
        }
    }

    @FXML
    @Override
    public void onDelete(ActionEvent event) {
        group = tableGroup.getSelectionModel().getSelectedItem();

        if (group != null) {

            Optional<ButtonType> confirm = MessagesUtil.showMessageConfirmation("Você deseja remover o group " + group.getName());

            if (confirm.get() == ButtonType.OK) {
                groupRepository.delete(group);
                initTable();
            }
        } else {
            MessagesUtil.showMessageWarning("Selecione o group que deseja deletar");
        }
    }

    @FXML
    @Override
    public void onNew(ActionEvent event) {
        stageManager.switchScene(root, EFxmlView.GROUP);
        txtName.requestFocus();
    }


    @Override
    public void initListeners() {

    }

    @FXML
    @Override
    public void initTable() {
        colunmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableGroup.setItems(listGroup());
    }

    private ObservableList<Group> listGroup() {
        return FXCollections.observableArrayList(groupRepository.findAll());
    }

}
