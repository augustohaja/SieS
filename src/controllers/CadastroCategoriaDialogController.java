package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Categoria;
import models.Multiplicado;
import models.Unico;

public class CadastroCategoriaDialogController implements Initializable{
	ObservableList<String> tiposList = FXCollections.observableArrayList("Multiplicado","�nico");
	
	@FXML
	private Label labelId;
	@FXML
	private TextField textFieldDescricao;
	@FXML
	private ComboBox<String> comboTipo;
	@FXML
	private Button buttonConfirmar;
	@FXML
	private Button buttonCancelar;
	
	
	// servirá para abrir uma outra janela/pop up simultaneamente em nossa aplicação
	private Stage dialogStage;
	private boolean buttonConfirmarClicked = false;
	private Categoria categoria;
	
	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isButtonConfirmarClicked() {
		return buttonConfirmarClicked;
	}

	public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
		this.buttonConfirmarClicked = buttonConfirmarClicked;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
				
		this.categoria = categoria;	
		if (String.valueOf(categoria.getId())!=null)
			this.labelId.setText(String.valueOf(categoria.getId()));
		this.textFieldDescricao.setText(categoria.getNome());
		this.comboTipo.setValue(categoria.getTipoConsumo());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comboTipo.setItems(tiposList);
	}
	
	@FXML
	public void handleButtonConfirmar() {
		this.buttonConfirmarClicked = true;

		this.categoria.setNome(this.textFieldDescricao.getText());
		if (comboTipo.getSelectionModel().getSelectedItem().equals("Multiplicado")){
			this.categoria.setTipoConsumo(new Multiplicado());	
		} else {
			this.categoria.setTipoConsumo(new Unico());
		}

		this.dialogStage.close();
	}
	
	@FXML
	public void handleButtonCancelar() {
		this.buttonConfirmarClicked = false;
		this.dialogStage.close();
	}

}
