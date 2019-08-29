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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;
import projetointegrador.Util.EFxmlView;
import projetointegrador.Util.MessagesUtil;
import projetointegrador.config.StageManager;
import projetointegrador.model.Material;
import projetointegrador.repository.MaterialRepository;

import java.net.URL;
import java.security.Guard;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class MaterialController implements Initializable
{
    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtCondutividadeTermica;

    @FXML
    private TableView<Material> tableMaterial;


    @FXML
    private TableColumn<Material, Long> columnId;

    @FXML
    private TableColumn<Material, String> columnName;

    @FXML
    private TableColumn<Material, String> columnCondutividadeTermica;


    @Autowired
    private MaterialRepository materialRepository;

    @Lazy
    @Autowired
    private StageManager stageManager;

    private Material material = new Material();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //material = new Material();
        initTable();
    }

    @FXML
    void save(ActionEvent event)
    {
        if (material != null)
        {
            material.setNome(txtName.getText());
            material.setCondutividade_termica(txtCondutividadeTermica.getText());

            try
            {
                materialRepository.save(material);
            }
            catch (TransactionSystemException e)
            {
                //TODO
                System.out.println(e.getMessage());
            }

            stageManager.switchScene(root, EFxmlView.GROUP_TABLE);
        }

        material = new Material();
    }

    @FXML
    void cancel(ActionEvent event) {
        stageManager.switchScene(root, EFxmlView.GROUP_TABLE);
    }

    @FXML
    void newGroup(ActionEvent event)
    {
        stageManager.switchScene(root, EFxmlView.GROUP);
        txtName.requestFocus();
    }

    @FXML
    void edit(ActionEvent event)
    {
        material = tableMaterial.getSelectionModel().getSelectedItem();

        if (material != null)
        {
            stageManager.switchScene(root, EFxmlView.GROUP);
            txtName.setText(material.getNome());
            txtCondutividadeTermica.setText(String.valueOf(material.getCondutividade_termica()));
        } else {
            MessagesUtil.showMessageWarning("Selecione o grupo que deseja editar");
        }
    }

    @FXML
    void delete(ActionEvent event)
    {
        material = tableMaterial.getSelectionModel().getSelectedItem();

        if (material != null)
        {

            Optional<ButtonType> confirm = MessagesUtil.showMessageConfirmation("Você deseja remover o material " + material.getNome());

            if (confirm.get() == ButtonType.OK)
            {
                materialRepository.delete(material);
                initTable();
            }
        }
        else {
            MessagesUtil.showMessageWarning("Selecione o material que deseja deletar");
        }

    }

    private ObservableList<Material> listMaterial()
    {
        return FXCollections.observableArrayList(materialRepository.findAll());
    }

    private void initTable()
    {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCondutividadeTermica.setCellValueFactory(new PropertyValueFactory<>("condutividade_termica"));
        tableMaterial.setItems(listMaterial());
    }

}
