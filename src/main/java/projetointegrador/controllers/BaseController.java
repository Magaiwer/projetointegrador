package projetointegrador.controllers;

import javafx.event.ActionEvent;

public interface BaseController<T> {

    void onCancel(ActionEvent event);
    void onSave(ActionEvent event);
    void onEdit(ActionEvent event);
    void onDelete(ActionEvent event);
    void onNew(ActionEvent event);

    void initListeners();
    void initTable();


}
