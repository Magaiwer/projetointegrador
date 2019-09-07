package projetointegrador.validation;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

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

    private static void addValidator(JFXTextField field) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage(field.getPromptText() + " é obrigatório");
        field.getValidators().add(validator);
    }
}
