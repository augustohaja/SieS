
package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.CategoriaDAO;
import dao.MaterialDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.Categoria;
import models.CategoriaDireto;
import models.CategoriaIndireto;
import models.Material;
import tools.DoubleValidator;
import tools.ToolMessage;

public class CadastroMaterialController implements Initializable {	
	@FXML
	private AnchorPane aPane;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancela;
	@FXML
	private ComboBox<String> comboCategoria;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtPreco;
	@FXML
	private TextField txtQuantidade;
	@FXML
	private TextField txtRendimento;
	@FXML
	private TextField txtUnidade;
	private ObservableList<String> categoriaObservableList;
	private List<Categoria> loadCategoria;
	private List<String> listaCategorias = new ArrayList<String>();
	

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	CategoriaDAO categoriaDAO = new CategoriaDAO();
		this.loadCategoria = categoriaDAO.all();
		
		for (Categoria categorias : loadCategoria) {
			listaCategorias.add(categorias.getNome());
		}
		
		this.categoriaObservableList  = FXCollections.observableArrayList(listaCategorias);
		this.comboCategoria.getItems().addAll(this.categoriaObservableList);
    }
    
	@FXML
	public void handleButtonCancel() throws IOException {
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/material.fxml"));
        this.aPane.getChildren().setAll(ap);
	}
	
	@FXML
	public void handleButtonCadastrar() throws IOException {
		Material material = new Material();
		MaterialDAO dao = new MaterialDAO();
		if (!txtNome.getText().equals("") && 
				!txtPreco.getText().equals("") && 
				!txtQuantidade.getText().equals("") && 
				!txtRendimento.getText().equals("") &&
				!txtUnidade.getText().equals("") &&
				!comboCategoria.getSelectionModel().isEmpty()){
			
			DoubleValidator db = new DoubleValidator();
			if(db.validate(this.txtPreco.getText()) && 
			   db.validate(this.txtQuantidade.getText()) &&
			   db.validate(this.txtRendimento.getText())) {
				
				material.setNome(this.txtNome.getText());
				material.setPreco(Double.parseDouble(this.txtPreco.getText()));
				material.setQtdEmbalagem(Double.parseDouble(this.txtQuantidade.getText()));
				material.setCoefM2(Double.parseDouble(this.txtRendimento.getText()));
				material.setUndMedida(this.txtUnidade.getText());
				
				material.setCategoria(getCategoria(comboCategoria.getSelectionModel().getSelectedItem()));
				
				dao.insert(material);;
				ToolMessage.showInformationMessage("","Cadastro realizado com sucesso!");
				limpaCamposCadastro();	
			} else {
				ToolMessage.showInformationMessage("","Insira um valor inteiro ou decimal");
			}	
			
		} else {
			ToolMessage.showInformationMessage("","Não foi possível realizar o seu cadastro");
		}
	}
	
	public Categoria getCategoria(String nome){
		CategoriaDAO catDAO = new CategoriaDAO();
		return catDAO.searchByName(comboCategoria.getSelectionModel().getSelectedItem()).get(0);	
	}
	
	public void limpaCamposCadastro() {
		txtNome.setText("");
		txtPreco.setText("");
		txtQuantidade.setText("");
		txtRendimento.setText("");
		txtUnidade.setText("");
	}
}
