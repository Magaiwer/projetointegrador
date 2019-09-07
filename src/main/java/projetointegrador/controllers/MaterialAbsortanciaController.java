package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import projetointegrador.Util.EFxmlView;
import projetointegrador.Util.MessagesUtil;
import projetointegrador.config.StageManager;
import projetointegrador.model.MaterialAbsortancia;
import projetointegrador.repository.MaterialAbsortanciaRepository;
import projetointegrador.service.MaterialAbsortanciaService;
import projetointegrador.service.exception.FieldRequeridException;

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
    private TableColumn<MaterialAbsortancia, BigDecimal> columnAlfaIni;

    @FXML
    private TableColumn<MaterialAbsortancia, BigDecimal> columnAlfaFim;

    @FXML
    private TableColumn<MaterialAbsortancia, BigDecimal> columnBetaIni;

    @FXML
    private TableColumn<MaterialAbsortancia, BigDecimal> columnBetaFim;

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
    private JFXTextField txtAlfaIni;

    @FXML
    private JFXTextField txtAlfaFim;

    @FXML
    private JFXTextField txtBetaIni;

    @FXML
    private JFXTextField txtBetaFim;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private MaterialAbsortanciaRepository materialAbsortanciaRepository;

    @Autowired
    private MaterialAbsortanciaService materialAbsortanciaService;

    private MaterialAbsortancia materialAbsortancia = new MaterialAbsortancia();
    private RequiredFieldValidator requiredFieldValidator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        if (txtAlfaIni != null) {
            initListeners();
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        stageManager.switchScene(root, EFxmlView.MATERIAL_ABSORTANCIA_TABLE);
    }

    @FXML
    void save(ActionEvent event) {

        if (materialAbsortancia != null) {
            materialAbsortancia.setSuperficie(txtSuperficie.getText());
            materialAbsortancia.setAlfaIni(new BigDecimal(Double.parseDouble(txtAlfaIni.getText().isEmpty() ? "0.0" :txtAlfaIni.getText())));
            materialAbsortancia.setAlfaFim(new BigDecimal(Double.parseDouble(txtAlfaFim.getText().isEmpty() ? "0.0" :txtAlfaFim.getText())));
            materialAbsortancia.setBetaIni(new BigDecimal(Double.parseDouble(txtBetaIni.getText().isEmpty() ? "0.0" :txtBetaIni.getText())));
            materialAbsortancia.setBetaFim(new BigDecimal(Double.parseDouble(txtBetaFim.getText().isEmpty() ? "0.0" :txtBetaFim.getText())));
            materialAbsortancia.setSuperficie(txtSuperficie.getText());

            try {
                materialAbsortanciaService.save(materialAbsortancia);
                MessagesUtil.showMessageInformation("Superficie cadastrada com sucesso");
                stageManager.switchScene(root, EFxmlView.MATERIAL_ABSORTANCIA_TABLE);

            } catch (FieldRequeridException e) {
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
            stageManager.switchScene(root, EFxmlView.MATERIAL_ABSORTANCIA);
            txtSuperficie.setText(materialAbsortancia.getSuperficie());
            txtAlfaIni.setText(String.valueOf(materialAbsortancia.getAlfaIni()));
            txtAlfaFim.setText(String.valueOf(materialAbsortancia.getAlfaFim()));
            txtBetaIni.setText(String.valueOf(materialAbsortancia.getBetaIni()));
            txtBetaFim.setText(String.valueOf(materialAbsortancia.getBetaFim()));

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
        columnAlfaIni.setCellValueFactory(new PropertyValueFactory<>("alfaIni"));
        columnAlfaFim.setCellValueFactory(new PropertyValueFactory<>("alfaFim"));
        columnBetaIni.setCellValueFactory(new PropertyValueFactory<>("betaIni"));
        columnBetaFim.setCellValueFactory(new PropertyValueFactory<>("betaFim"));
        tableMaterialAbsortancia.setItems(listAbsortancias());
    }

    private void initListeners() {
        txtAlfaIni.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    txtAlfaIni.setText(oldValue);
                }
            }
        });
        txtAlfaFim.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    txtAlfaFim.setText(oldValue);
                }
            }
        });
        txtBetaIni.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    txtBetaIni.setText(oldValue);
                }
            }
        });
        txtBetaFim.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    txtBetaFim.setText(oldValue);
                }
            }
        });
    }
}