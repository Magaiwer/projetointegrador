package projetointegrador.Util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MessagesUtil {


    private static void showMessage(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(message);
        alert.show();
    }

    public static void showMessageInformation(String message) {
        showMessage(message, Alert.AlertType.INFORMATION);
    }

    public static void showMessageWarning(String message) {
        showMessage(message, Alert.AlertType.WARNING);
    }

    public static void showMessageError(String message) {
        showMessage(message, Alert.AlertType.ERROR);
    }

    public static Optional<ButtonType> showMessageConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(message);
        return alert.showAndWait();
    }
}
