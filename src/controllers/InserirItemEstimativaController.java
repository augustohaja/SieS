package controllers;

import java.io.IOException;
import java.net.URL;
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
import models.Estimativa;
import models.ItemEstimativa;
import models.Material;

public class InserirItemEstimativaController implements Initializable {

	@FXML
	private AnchorPane aPane;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancela;
	@FXML
	private ComboBox<String> comboMaterial;
	@FXML
	private TextField txtQuantidade;
	
	private Estimativa estimativa;
	
	private List<Material> loadMaterial;
	
	private List<String> listaMaterial;
	
	private ObservableList<String> materialObservableList;

	public Estimativa getEstimativa() {
		return estimativa;
	}

	public void setEstimativa(Estimativa estimativa) {
		this.estimativa = estimativa;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		MaterialDAO materialDAO = new MaterialDAO();
		this.loadMaterial = materialDAO.all();
		
		for (Material material : loadMaterial) {
			listaMaterial.add(material.getNome());
		}
		
		this.materialObservableList = FXCollections.observableArrayList(listaMaterial);
		this.comboMaterial.getItems().addAll(this.materialObservableList);
	}
	
	public void handleButtonCadastrar() throws IOException {
		ItemEstimativa itemEstimativa = new ItemEstimativa();
		itemEstimativa.setMaterial(getMaterial(comboMaterial.getSelectionModel().getSelectedItem()));
		itemEstimativa.setQtd(Double.parseDouble(this.txtQuantidade.getText()));
		this.estimativa.setItemEstimativa(itemEstimativa);
		
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/cadastroItemEstimativa.fxml"));
        this.aPane.getChildren().setAll(ap);
	}
	
	public Material getMaterial(String nome){
		MaterialDAO matDAO = new MaterialDAO();
		return matDAO.searchByName(comboMaterial.getSelectionModel().getSelectedItem()).get(0);	
	}
	
	@FXML
	public void handleButtonVoltar() throws IOException {
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/cadastroItemEstimativa.fxml"));
        this.aPane.getChildren().setAll(ap);
	}
	
	
	
}
