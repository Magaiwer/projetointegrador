package projetointegrador.controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projetointegrador.Util.Formatter;
import projetointegrador.model.Audit;
import projetointegrador.repository.AuditRepository;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AuditoriaController implements Initializable {
    @FXML
    private TableView<Audit> tableAudit;

    @FXML
    private TableColumn<Audit, Long> columnId;

    @FXML
    private TableColumn<Audit, String> columnNewValue;

    @FXML
    private TableColumn<Audit, String> columnCommand;

    @FXML
    private TableColumn<Audit, String> columnUserId;

    @FXML
    private TableColumn<Audit, String> columnCreatedAt;

    @FXML
    private JFXTextField txtFilter;

    @Autowired
    private AuditRepository auditRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    private ObservableList<Audit> listAudit() {
        return FXCollections.observableArrayList(auditRepository.findAllWithUsers());
    }

    private void initTable() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNewValue.setCellValueFactory(new PropertyValueFactory<>("newValue"));
        columnCommand.setCellValueFactory(new PropertyValueFactory<>("command"));

        columnUserId.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getUser().getName()));
        columnCreatedAt.setCellValueFactory(param ->
                new SimpleStringProperty(Formatter.getDateTextFormated(param.getValue().getCreatedAt(), Formatter.FORMAT_PT_BR_WITH_TIME)));


        FilteredList<Audit> auditFilteredList = new FilteredList<>(listAudit(), audit -> true);

        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> auditFilteredList.setPredicate(audit ->
        {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String filterLowerCase = newValue.toLowerCase();
            return predicate(audit, filterLowerCase);
        }));



        SortedList<Audit> auditSortedList = new SortedList<>(auditFilteredList);
        auditSortedList.comparatorProperty().bind(tableAudit.comparatorProperty());
        tableAudit.setItems(auditSortedList);
    }

    private boolean predicate(Audit audit, String filterLowerCase) {
        String date = Formatter.getDateTextFormated(audit.getCreatedAt(), Formatter.FORMAT_PT_BR_WITH_TIME);

        return date.contains(filterLowerCase)
                || audit.getId().toString().contains(filterLowerCase)
                || audit.getNewValue().toLowerCase().contains(filterLowerCase)
                || audit.getUser().getName().toLowerCase().contains(filterLowerCase)
                || audit.getCommand().name().toLowerCase().contains(filterLowerCase);
    }
}
