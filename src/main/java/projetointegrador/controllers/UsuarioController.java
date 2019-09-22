package projetointegrador.controllers;

import com.jfoenix.controls.*;
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
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import projetointegrador.Util.EFxmlView;
import projetointegrador.Util.MessagesUtil;
import projetointegrador.config.StageManager;
import projetointegrador.model.Group;
import projetointegrador.model.User;
import projetointegrador.repository.GroupRepository;
import projetointegrador.repository.UserRepository;
import projetointegrador.service.UserService;
import projetointegrador.service.exception.EmailJaCadastradoException;
import projetointegrador.service.exception.PasswordInvalidException;
import projetointegrador.service.exception.RequiredPasswordException;
import projetointegrador.validation.EntityValidator;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class UsuarioController implements Initializable, BaseController<UsuarioController> {

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXToggleButton btnAtivo;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private AnchorPane panelUser;

    @FXML
    private JFXPasswordField txtConfirmePassword;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXComboBox<Group> cboxGroups;

    @FXML
    private JFXButton btnAddGroup;

    @FXML
    private JFXChipView<Group> cViewGrupo;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<User> tableUser;


    @FXML
    private TableColumn<User, Long> colunmId;

    @FXML
    private TableColumn<User, String> colunmName;

    @FXML
    private TableColumn<User, String> colunmEmail;

    @FXML
    private TableColumn<User, String> colunmActive;

    @FXML
    private JFXButton btnNewUser;

    @FXML
    private JFXButton btnEditUser;

    @FXML
    private JFXButton btnDeleteUser;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    @Lazy
    @Autowired
    private StageManager stageManager;

    private User user = new User();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        initCombo();
        initConverter();

        if (txtName != null) {
            EntityValidator.noEmpty(txtName, txtEmail);
        }
    }

    @FXML
    void addGroup(ActionEvent event) {
        Group group = cboxGroups.getSelectionModel().getSelectedItem();

        if (!cViewGrupo.getChips().contains(group)) {
            cViewGrupo.getChips().add(group);
        }
    }

    @FXML
    @Override
    public void onCancel(ActionEvent event) {
        stageManager.switchScene(root, EFxmlView.USER_TABLE);
    }

    @FXML
    @Override
    public void onSave(ActionEvent event) {
        boolean noEmpty = EntityValidator.noEmpty(txtName, txtEmail);

        if (user != null && noEmpty) {
            bindUser();

            try {
                userService.save(user);
                MessagesUtil.showMessageInformation("Usuário salvo com sucesso");
                stageManager.switchScene(root, EFxmlView.USER_TABLE);

            } catch (PasswordInvalidException | RequiredPasswordException | EmailJaCadastradoException e) {
                MessagesUtil.showMessageError(e.getMessage());
            }
        }
    }

    @FXML
    @Override
    public void onEdit(ActionEvent event) {
        user = tableUser.getSelectionModel().getSelectedItem();

        if (user != null) {
            stageManager.switchScene(root, EFxmlView.USER);
            Optional<User> usuarioOptional = userRepository.findUserGroups(user.getId());

            usuarioOptional.ifPresent(u -> user = u);
            usuarioOptional.ifPresent(u -> cViewGrupo.getChips().addAll(u.getGroups()));

            txtName.setText(user.getName());
            txtEmail.setText(user.getLogin());
            txtEmail.setDisable(true);
            btnAtivo.setSelected(user.isActive());

        } else {
            MessagesUtil.showMessageWarning("Selecione um Usuário");
        }
    }

    @FXML
    @Override
    public void onDelete(ActionEvent event) {
        User user = tableUser.getSelectionModel().getSelectedItem();

        if (user != null) {
            Optional<ButtonType> confirm = MessagesUtil.showMessageConfirmation("Você deseja remover o usuário " + user.getName());

            if (confirm.get() == ButtonType.OK) {
                userRepository.delete(user);
                initTable();
            }

        } else {
            MessagesUtil.showMessageWarning("Selecione o usúario que deseja editar");
        }
    }

    @FXML
    @Override
    public void onNew(ActionEvent event) {
        stageManager.switchScene(root, EFxmlView.USER);
        user = new User();
        txtName.requestFocus();
        initCombo();
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initTable() {
        colunmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colunmEmail.setCellValueFactory(new PropertyValueFactory<>("login"));
        colunmActive.setCellValueFactory(new PropertyValueFactory<>("labelActive"));
        tableUser.setItems(listUsers());
    }

    private void initCombo() {
        if (cboxGroups != null) {
            cboxGroups.setItems(listGroups());
        }
    }

    private void initConverter() {
        if (cboxGroups != null) {

            StringConverter<Group> grupoStringConverter = new StringConverter<Group>() {
                @Override
                public String toString(Group grupo) {
                    return grupo.getName();
                }

                @Override
                public Group fromString(String string) {
                    return null;
                }
            };
            cboxGroups.setConverter(grupoStringConverter);
            cViewGrupo.setConverter(grupoStringConverter);
        }
    }

    private ObservableList<User> listUsers() {
        return FXCollections.observableArrayList(userRepository.findAll());
    }

    private ObservableList<Group> listGroups() {
        return FXCollections.observableArrayList(groupRepository.findAll());
    }

    private void bindUser() {
        user.setName(txtName.getText());
        user.setLogin(txtEmail.getText());
        user.setActive(btnAtivo.isSelected());
        user.setPassword(txtPassword.getText());
        user.setConfimationPassword(txtConfirmePassword.getText());
        user.setGroups(cViewGrupo.getChips());
    }

}
