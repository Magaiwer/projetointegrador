package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import projetointegrador.Util.EFxmlView;
import projetointegrador.config.StageManager;
import projetointegrador.model.Usuario;
import projetointegrador.repository.UsuarioRepository;

import java.net.URL;
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

    private Usuario usuario = new Usuario();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    @FXML
    void save(ActionEvent event) {
        if (usuario != null) {
            usuario.setNome(txtName.getText());
            usuario.setLogin(txtEmail.getText());
            usuario.setSenha(txtPassword.getText());
            usuario.setAtivo(btnAtivo.isSelected());
            usuarioRepository.save(usuario);
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
        txtName.requestFocus();
    }

    @FXML
    void edit(ActionEvent event) {
        usuario = tableUser.getSelectionModel().getSelectedItem();

        if (usuario != null) {
            stageManager.switchScene(root, EFxmlView.USER);
            txtName.setText(usuario.getNome());
            txtEmail.setText(usuario.getNome());
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
            usuarioRepository.delete(usuario);
            initTable();
        } else {
            // TODO
            System.out.println("SELECIONE UM USUARIO");
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

}
