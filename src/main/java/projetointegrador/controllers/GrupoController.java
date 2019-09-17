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
import projetointegrador.model.Grupo;
import projetointegrador.repository.GrupoRepository;
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
    private TableView<Grupo> tableGroup;


    @FXML
    private TableColumn<Grupo, Long> colunmId;

    @FXML
    private TableColumn<Grupo, String> colunmName;

    @FXML
    private JFXButton btnEditGroup;

    @FXML
    private JFXButton btnNewGroup;

    @FXML
    private JFXButton btnDeleteGroup;


    @Autowired
    private GrupoRepository grupoRepository;

    @Lazy
    @Autowired
    private StageManager stageManager;

    private static Logger LOGGER = LoggerFactory.getLogger(Grupo.class);

    private Grupo grupo = new Grupo();

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

        if (grupo != null && noEmpty) {
            grupo.setNome(txtName.getText());
            try {
                grupoRepository.save(grupo);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }

            stageManager.switchScene(root, EFxmlView.GROUP_TABLE);
        }
        grupo = new Grupo();
    }

    @FXML
    @Override
    public void onEdit(ActionEvent event) {
        grupo = tableGroup.getSelectionModel().getSelectedItem();

        if (grupo != null) {
            stageManager.switchScene(root, EFxmlView.GROUP);
            txtName.setText(grupo.getNome());
        } else {
            MessagesUtil.showMessageWarning("Selecione o grupo que deseja editar");
        }
    }

    @FXML
    @Override
    public void onDelete(ActionEvent event) {
        grupo = tableGroup.getSelectionModel().getSelectedItem();

        if (grupo != null) {

            Optional<ButtonType> confirm = MessagesUtil.showMessageConfirmation("Você deseja remover o grupo " + grupo.getNome());

            if (confirm.get() == ButtonType.OK) {
                grupoRepository.delete(grupo);
                initTable();
            }
        } else {
            MessagesUtil.showMessageWarning("Selecione o grupo que deseja deletar");
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
        colunmName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableGroup.setItems(listGroup());
    }

    private ObservableList<Grupo> listGroup() {
        return FXCollections.observableArrayList(grupoRepository.findAll());
    }

}
