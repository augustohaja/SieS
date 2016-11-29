
package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.MaterialDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Material;
import tools.DoubleValidator;
import tools.ToolMessage;

public class EditaMaterialController implements Initializable {	
	private Stage dialogStage;
	@FXML
	private AnchorPane aPane;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnCancela;
	@FXML
	private TextField txtNome2;
	@FXML
	private TextField txtPreco2;
	@FXML
	private TextField txtQuantidade2;
	@FXML
	private TextField txtRendimento2;
	@FXML
	private TextField txtUnidade2;
	private Material material;
	
	public void setMaterial(Material materiall) {
		this.material = materiall;
	}
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
    public void start(){
    	System.out.println(this.material.getNome());
    	
    	this.txtNome2.setText(this.material.getNome());
    	txtPreco2.setText(this.material.getPreco().toString());
    	txtQuantidade2.setText(this.material.getQtdEmbalagem().toString());
    	txtRendimento2.setText(this.material.getCoefM2().toString());
    	txtUnidade2.setText(this.material.getUndMedida());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	//start();
    }
    
	@FXML
	public void handleButtonCancel() throws IOException {
		this.dialogStage.close();
	}
	
	@FXML
	public void handleButtonEditar() throws IOException {
		
		MaterialDAO dao = new MaterialDAO();
		if (!txtNome2.getText().equals("") && 
				!txtPreco2.getText().equals("") && 
				!txtQuantidade2.getText().equals("") && 
				!txtRendimento2.getText().equals("") &&
				!txtUnidade2.getText().equals("")){
			
			DoubleValidator db = new DoubleValidator();
			if(db.validate(this.txtPreco2.getText()) && 
			   db.validate(this.txtQuantidade2.getText()) &&
			   db.validate(this.txtRendimento2.getText())) {
				
				this.material.setNome(this.txtNome2.getText());
				this.material.setPreco(Double.parseDouble(this.txtPreco2.getText()));
				this.material.setQtdEmbalagem(Double.parseDouble(this.txtQuantidade2.getText()));
				this.material.setCoefM2(Double.parseDouble(this.txtRendimento2.getText()));
				this.material.setUndMedida(this.txtUnidade2.getText());
				//this.material.setCategoria(this.categoria);
				
				dao.update(material);;
				ToolMessage.showInformationMessage("","Edição realizada com sucesso!");
				limpaCamposCadastro();	
			} else {
				ToolMessage.showInformationMessage("","Insira um valor inteiro ou decimal");
			}	
			
		} else {
			ToolMessage.showInformationMessage("","Não foi possível realizar o update");
		}
	}
	
	public void limpaCamposCadastro() {
		txtNome2.setText("");
		txtPreco2.setText("");
		txtQuantidade2.setText("");
		txtRendimento2.setText("");
		txtUnidade2.setText("");
	}
}
