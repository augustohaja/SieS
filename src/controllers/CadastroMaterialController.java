
package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class CadastroMaterialController implements Initializable {	
	@FXML
	private AnchorPane aPane;
	@FXML
	private Button btnCancela;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
	@FXML
	public void handleButtonCancel() throws IOException {
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/menu.fxml"));
        this.aPane.getChildren().setAll(ap);
	}
	
}
