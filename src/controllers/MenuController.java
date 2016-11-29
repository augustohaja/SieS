
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.UsuarioSessao;


public class MenuController implements Initializable {
	private Stage dialogStage;
	
	@FXML
	private Label lblNomeUser;
	@FXML
	private MenuBar menuBar;
	@FXML
	private MenuItem menuCadastroMaterial;
	@FXML
	private MenuItem menuCadastroCategoria;
	@FXML
	private MenuItem menuEstimativa;
	@FXML
	private AnchorPane aPane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	start();
    }
    
    public void start(){  	
    	this.lblNomeUser.setText(String.valueOf(UsuarioSessao.getUser().getNome()));
    }
	
	public Stage getDialogStage() {
		return dialogStage;
	}
    
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
    public void handleMenuCadastroMaterial() throws IOException {
		System.out.println("Materiais");
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/material.fxml"));
        this.aPane.getChildren().setAll(ap);
    }
	
	@FXML
    public void handleMenuCadastroCategoria() throws IOException {
		System.out.println("Categorias");
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/cadastroCategoria.fxml"));
        this.aPane.getChildren().setAll(ap);
    }

}
