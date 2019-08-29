package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import projetointegrador.Util.EFxmlView;
import projetointegrador.Util.MessagesUtil;
import projetointegrador.config.StageManager;
import projetointegrador.model.MaterialAbsortancia;
import projetointegrador.repository.MaterialAbsortanciaRepository;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class MaterialAbsortanciaController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<MaterialAbsortancia> tableMaterialAbsortancia;

    @FXML
    private TableColumn<MaterialAbsortancia, Long> columnId;

    @FXML
    private TableColumn<MaterialAbsortancia, String> columnSuperficie;

    @FXML
    private TableColumn<MaterialAbsortancia, BigDecimal> columnAlfa;

    @FXML
    private TableColumn<MaterialAbsortancia, BigDecimal> columnBeta;

    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private AnchorPane panel;

    @FXML
    private JFXTextField txtSuperficie;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXTextField txtAlfa;

    @FXML
    private JFXTextField txtBeta;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private MaterialAbsortanciaRepository materialAbsortanciaRepository;

    private MaterialAbsortancia materialAbsortancia = new MaterialAbsortancia();
    private RequiredFieldValidator requiredFieldValidator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    @FXML
    void cancel(ActionEvent event) {
        stageManager.switchScene(root, EFxmlView.MATERIAL_ABSORTANCIA_TABLE);
    }

    @FXML
    void save(ActionEvent event) {

        if (materialAbsortancia != null) {
            materialAbsortancia.setSuperficie(txtSuperficie.getText());
            materialAbsortancia.setAlfa(new BigDecimal(txtAlfa.getText()));
            materialAbsortancia.setSuperficie(txtSuperficie.getText());

            try {
                materialAbsortanciaRepository.save(materialAbsortancia);
                MessagesUtil.showMessageWarning("Superficie cadastrada com sucesso");
                stageManager.switchScene(root, EFxmlView.MATERIAL_ABSORTANCIA_TABLE);

            } catch (Exception e) {
                //TODO
                MessagesUtil.showMessageError(e.getMessage());
            }
        }
    }

    @FXML
    void delete(ActionEvent event) {
        MaterialAbsortancia materialAbsortancia = tableMaterialAbsortancia.getSelectionModel().getSelectedItem();

        if (materialAbsortancia != null) {
            Optional<ButtonType> confirm = MessagesUtil.showMessageConfirmation("Você deseja remover a superficie " + materialAbsortancia.getSuperficie());

            if (confirm.get() == ButtonType.OK) {
                materialAbsortanciaRepository.delete(materialAbsortancia);
                initTable();
            }

        } else {
            MessagesUtil.showMessageWarning("Selecione a superficie que deseja remover");
        }

    }

    @FXML
    void edit(ActionEvent event) {
        materialAbsortancia = tableMaterialAbsortancia.getSelectionModel().getSelectedItem();

        if (materialAbsortancia != null) {
            stageManager.switchScene(root, EFxmlView.USER);
            txtSuperficie.setText(materialAbsortancia.getSuperficie());
            txtAlfa.setText(String.valueOf(materialAbsortancia.getAlfa()));
            txtBeta.setText(String.valueOf(materialAbsortancia.getBeta()));

        } else {
            MessagesUtil.showMessageWarning("Selecione a superfice");
        }
    }

    @FXML
    void newAbsortancia(ActionEvent event) {
        stageManager.switchScene(root, EFxmlView.MATERIAL_ABSORTANCIA);
        materialAbsortancia = new MaterialAbsortancia();
        txtSuperficie.requestFocus();
    }

    private ObservableList<MaterialAbsortancia> listAbsortancias() {
        return FXCollections.observableArrayList(materialAbsortanciaRepository.findAll());
    }

    private void initTable() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnSuperficie.setCellValueFactory(new PropertyValueFactory<>("superficie"));
        columnAlfa.setCellValueFactory(new PropertyValueFactory<>("alfa"));
        columnBeta.setCellValueFactory(new PropertyValueFactory<>("beta"));
        tableMaterialAbsortancia.setItems(listAbsortancias());
    }

}