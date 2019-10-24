package projetointegrador.validation;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;

import java.util.ArrayList;
import java.util.List;

public class EntityValidator {

    public static boolean noEmpty(JFXTextField... textFields) {

        List<String> hasFieldsErrors = new ArrayList<>();

        for (JFXTextField field : textFields) {

            addValidator(field);

            field.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) {
                    field.validate();
                }
            });

            if (field.getText().trim().isEmpty()) {
                hasFieldsErrors.add(field.getPromptText());
            }

        }
        return hasFieldsErrors.isEmpty();

    }

    public static boolean noSelectItem(JFXComboBox... comboBoxes) {
        List<String> hasFieldsErrors = new ArrayList<>();

        for (JFXComboBox comboBox : comboBoxes) {

            RequiredFieldValidator validator = new RequiredFieldValidator();
            validator.setMessage(comboBox.getPromptText() + " é obrigatório");
            comboBox.setValidators(validator);

            ChangeListener<? super Boolean> changeListener = (observable, oldValue, newValue) -> {
                if(!newValue) {
                    comboBox.validate();
                }
            };

            comboBox.focusColorProperty().addListener(changeListener);

            if (comboBox.getSelectionModel().isEmpty()) {
                hasFieldsErrors.add(comboBox.getPromptText());
            };
        }
        return hasFieldsErrors.isEmpty();
    }

    public static void clearFields(JFXTextField... fields) {
        for (JFXTextField field : fields) {
            field.clear();
        }
    }

    private static void addValidator(JFXTextField field) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage(field.getPromptText() + " é obrigatório");
        field.getValidators().add(validator);
    }

}
