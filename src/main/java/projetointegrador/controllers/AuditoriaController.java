package projetointegrador.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projetointegrador.model.Audit;
import java.net.URL;
import java.util.ResourceBundle;

import projetointegrador.repository.AuditRepository;

@Component
public class AuditoriaController implements Initializable
{
    @FXML
    private TableView<Audit> tableAudit;

    @FXML
    private TableColumn<Audit, Long> colunmId;

    @FXML
    private TableColumn<Audit, String> colunmOldValue;

    @FXML
    private TableColumn<Audit, String> colunmNewValue;

    @FXML
    private TableColumn<Audit, String> colunmCommand;

    @FXML
    private TableColumn<Audit, String> colunmUserId;

    @Autowired
    private AuditRepository auditRepository;

    private Audit audit = new Audit();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initTable();
    }

    private ObservableList<Audit> listAudit()
    {
        return FXCollections.observableArrayList(auditRepository.findAll());
    }

    private void initTable()
    {
        colunmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunmNewValue.setCellValueFactory(new PropertyValueFactory<>("new_value"));
        colunmCommand.setCellValueFactory(new PropertyValueFactory<>("command"));
        colunmUserId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        tableAudit.setItems(listAudit());
    }
}
