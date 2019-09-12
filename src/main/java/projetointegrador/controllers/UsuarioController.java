package projetointegrador.controllers;

import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ObservableValue;
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
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import projetointegrador.Util.EFxmlView;
import projetointegrador.Util.MessagesUtil;
import projetointegrador.annotation.Restriction;
import projetointegrador.config.StageManager;
import projetointegrador.model.Grupo;
import projetointegrador.model.Usuario;
import projetointegrador.repository.GrupoRepository;
import projetointegrador.repository.UsuarioRepository;
import projetointegrador.service.UsuarioService;
import projetointegrador.service.exception.EmailJaCadastradoException;
import projetointegrador.service.exception.GenericException;
import projetointegrador.service.exception.PasswordInvalidException;
import projetointegrador.service.exception.RequiredPasswordException;
import projetointegrador.validation.EntityValidator;

import java.net.URL;
import java.security.Guard;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class UsuarioController implements Initializable {

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
    private JFXComboBox<Grupo> cboxGroups;

    @FXML
    private JFXButton btnAddGroup;

    @FXML
    private JFXChipView<Grupo> cViewGrupo;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Usuario> tableUser;


    @FXML
    private TableColumn<Usuario, Long> colunmId;

    @FXML
    private TableColumn<Usuario, String> colunmName;

    @FXML
    private TableColumn<Usuario, String> colunmEmail;

    @FXML
    private TableColumn<Usuario, String> colunmActive;

    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnDelete;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Lazy
    @Autowired
    private StageManager stageManager;

    private Usuario usuario = new Usuario();
    private RequiredFieldValidator requiredFieldValidator;


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
    void save(ActionEvent event) {
        boolean noEmpty = EntityValidator.noEmpty(txtName, txtEmail);

        if (usuario != null && noEmpty) {
            bindUser();

            try {
                usuarioService.save(usuario);
                MessagesUtil.showMessageInformation("Usuário salvo com sucesso");
                stageManager.switchScene(root, EFxmlView.USER_TABLE);

            } catch (PasswordInvalidException | RequiredPasswordException | EmailJaCadastradoException e) {
                MessagesUtil.showMessageError(e.getMessage());
            }
        }
    }


    @FXML
    void cancel(ActionEvent event) {
        stageManager.switchScene(root, EFxmlView.USER_TABLE);
    }

    @FXML
    void newUser(ActionEvent event) {
        stageManager.switchScene(root, EFxmlView.USER);
        usuario = new Usuario();
        txtName.requestFocus();
        initCombo();

    }

    @FXML
    void edit(ActionEvent event) {
        usuario = tableUser.getSelectionModel().getSelectedItem();

        if (usuario != null) {
            stageManager.switchScene(root, EFxmlView.USER);
            Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioWithGrupos(usuario.getId());

            usuarioOptional.ifPresent(u-> usuario = u);
            usuarioOptional.ifPresent(u-> cViewGrupo.getChips().addAll(u.getGrupos()));

            txtName.setText(usuario.getNome());
            txtEmail.setText(usuario.getLogin());
            txtEmail.setDisable(true);
            btnAtivo.setSelected(usuario.isAtivo());

        } else {
            MessagesUtil.showMessageWarning("Selecione um Usuário");
        }
    }

    @FXML
    void delete(ActionEvent event) {
        Usuario usuario = tableUser.getSelectionModel().getSelectedItem();

        if (usuario != null) {
            Optional<ButtonType> confirm = MessagesUtil.showMessageConfirmation("Você deseja remover o usuário " + usuario.getNome());

            if (confirm.get() == ButtonType.OK) {
                usuarioRepository.delete(usuario);
                initTable();
            }

        } else {
            MessagesUtil.showMessageWarning("Selecione o usúario que deseja editar");
        }

    }

    @FXML
    void addGroup(ActionEvent event) {
        Grupo grupo = cboxGroups.getSelectionModel().getSelectedItem();

        if (!cViewGrupo.getChips().contains(grupo)) {
            cViewGrupo.getChips().add(grupo);
        }
    }

    private void bindUser() {
        usuario.setNome(txtName.getText());
        usuario.setLogin(txtEmail.getText());
        usuario.setAtivo(btnAtivo.isSelected());
        usuario.setSenha(txtPassword.getText());
        usuario.setConfirmacaoSenha(txtConfirmePassword.getText());
        usuario.setGrupos(cViewGrupo.getChips());
    }

    private ObservableList<Usuario> listUsers() {
        return FXCollections.observableArrayList(usuarioRepository.findAll());
    }

    private ObservableList<Grupo> listGroups() {
        return FXCollections.observableArrayList(grupoRepository.findAllWithPermissions());
    }

    private void initTable() {
        colunmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunmName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunmEmail.setCellValueFactory(new PropertyValueFactory<>("login"));
        colunmActive.setCellValueFactory(new PropertyValueFactory<>("ativo"));
        tableUser.setItems(listUsers());
    }

    private void initCombo() {
        if (cboxGroups != null) {
            cboxGroups.setItems(listGroups());
        }
    }

    private void initConverter() {
        if (cboxGroups != null) {

            StringConverter<Grupo> grupoStringConverter = new StringConverter<Grupo>() {
                @Override
                public String toString(Grupo grupo) {
                    return grupo.getNome();
                }

                @Override
                public Grupo fromString(String string) {
                    return null;
                }
            };
            cboxGroups.setConverter(grupoStringConverter);
            cViewGrupo.setConverter(grupoStringConverter);
        }
    }


}
