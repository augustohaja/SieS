package tools;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ToolMessage {
	public static void showInformationMessage(String title, String message){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}
	
	public static Optional<ButtonType> showErrorMessage(String message){
		 Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
		confirmationAlert.setHeaderText(" ");
		confirmationAlert.setContentText(message);
		
		Optional<ButtonType> result = confirmationAlert.showAndWait();
		return result;
	}
	
}
