package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;
import projetointegrador.Util.EFxmlView;
import projetointegrador.config.StageManager;
import projetointegrador.model.Usuario;
import projetointegrador.repository.UsuarioRepository;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

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

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Usuario usuario = new Usuario();
    private RequiredFieldValidator requiredFieldValidator;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();

        if (txtName != null)
            validateForm();

    }

    @FXML
    void save(ActionEvent event) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        txtName.getValidators().add(validator);
        validator.setMessage("Campo é obrigatorio");
        txtName.validate();

        if (usuario != null) {
            usuario.setNome(txtName.getText());
            usuario.setLogin(txtEmail.getText());
            usuario.setSenha(passwordEncoder.encode(txtPassword.getText()));
            usuario.setAtivo(btnAtivo.isSelected());

            usuarioRepository.save(usuario);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Usuário salvo com sucesso");
            alert.show();
            stageManager.switchScene(root, EFxmlView.USER_TABLE);
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

    }

    @FXML
    void edit(ActionEvent event) {
        usuario = tableUser.getSelectionModel().getSelectedItem();

        if (usuario != null) {
            stageManager.switchScene(root, EFxmlView.USER);
            txtName.setText(usuario.getNome());
            txtEmail.setText(usuario.getLogin());
            txtPassword.setText(usuario.getNome());
            txtConfirmePassword.setText(usuario.getNome());
            btnAtivo.setSelected(usuario.isAtivo());

        } else {
            // TODO
            System.out.println("SELECIONE UM USUARIO");
        }
    }

    @FXML
    void delete(ActionEvent event) {
        Usuario usuario = tableUser.getSelectionModel().getSelectedItem();

        if (usuario != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Você deseja remover o usuário " + usuario.getNome());
            Optional<ButtonType> confirm = alert.showAndWait();

            if (confirm.get() == ButtonType.OK) {
                usuarioRepository.delete(usuario);
                initTable();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Selecione o usúario que deseja editar");
            alert.show();
        }

    }

    private ObservableList<Usuario> listUsers() {
        return FXCollections.observableArrayList(usuarioRepository.findAll());
    }

    private void initTable() {
        colunmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunmName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunmEmail.setCellValueFactory(new PropertyValueFactory<>("login"));
        colunmActive.setCellValueFactory(new PropertyValueFactory<>("ativo"));
        tableUser.setItems(listUsers());
    }

    private void validateForm() {
        requiredFieldValidator = new RequiredFieldValidator();
        txtName.getValidators().add(requiredFieldValidator);
        txtEmail.getValidators().add(requiredFieldValidator);
        txtPassword.getValidators().add(requiredFieldValidator);

        txtName.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue && newValue != oldValue) {
                requiredFieldValidator.setMessage("Campo obrigatório");
                txtName.validate();
            }
        });
        txtEmail.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue && newValue != oldValue) {
                requiredFieldValidator.setMessage("Campo obrigatório");
                txtEmail.validate();
            }
        });

    }

}
