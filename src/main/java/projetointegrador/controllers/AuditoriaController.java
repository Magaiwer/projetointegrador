package projetointegrador.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projetointegrador.model.Audit;
import projetointegrador.model.User;
import projetointegrador.repository.AuditRepository;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AuditoriaController implements Initializable
{
    @FXML
    private TableView<Audit> tableAudit;

    @FXML
    private TableColumn<Audit, Long> columnId;
/*
    @FXML
    private TableColumn<Audit, String> colunmOldValue;*/

    @FXML
    private TableColumn<Audit, String> columnNewValue;

    @FXML
    private TableColumn<Audit, String> columnCommand;

    @FXML
    private TableColumn<Audit, User> columnUserId;

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
        return FXCollections.observableArrayList(auditRepository.findAllWithUsers());
    }

    private void initTable()
    {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
       // columnId.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        columnNewValue.setCellValueFactory(new PropertyValueFactory<>("newValue"));
        columnCommand.setCellValueFactory(new PropertyValueFactory<>("command"));
        columnUserId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Audit, User>, ObservableValue<User>>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Audit, User> param) {
                return new SimpleStringProperty(param.getValue().getUser().getName());
            }
        });
        tableAudit.setItems(listAudit());
    }
}
