package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.ItemEstimativaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.ItemEstimativa;

public class CadastroItemEstimativaController implements Initializable  {

	@FXML
	private AnchorPane aPane;
	@FXML
	private Button btnInsere;
	@FXML
	private Button btnSair;
	@FXML
	private Button btnDeleta;
	@FXML
	private TableView<ItemEstimativa> tableViewCadastroItemEstimativa;
	@FXML
	private TableColumn<ItemEstimativa, String> cId;
	@FXML
	private TableColumn<ItemEstimativa, String> cDescricao;
	@FXML
	private TableColumn<ItemEstimativa, String> cQtdem2;
	@FXML
	private TableColumn<ItemEstimativa, String> cEstimado;
	@FXML
	private TableColumn<ItemEstimativa, String> cVlrTotal;
	
	private ObservableList<ItemEstimativa> ItemEstimativaObservableList;
	
	private List<ItemEstimativa> loadItemEstimativa;
	
	private ItemEstimativa itemEstimativa;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listaItemEstimativa();
        this.tableViewCadastroItemEstimativa.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectItemTableViewItemEstimativa(newValue));
	}
	
	public void selectItemTableViewItemEstimativa(ItemEstimativa itemEstimativa){
		this.itemEstimativa = itemEstimativa;
	}
	
	@FXML
	public void handleButtonCadastroMaterial() throws IOException {
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/inserirItemEstimativa.fxml"));
        this.aPane.getChildren().setAll(ap);
        this.tableViewCadastroItemEstimativa.refresh();
	}

	private void listaItemEstimativa() {
		ItemEstimativaDAO itemEstimativaDAO = new ItemEstimativaDAO();
		this.loadItemEstimativa = itemEstimativaDAO.all();
		
		this.cId.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.cDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		this.cQtdem2.setCellValueFactory(new PropertyValueFactory<>("qtd"));
		this.cEstimado.setCellValueFactory(new PropertyValueFactory<>("estimado"));
		this.cVlrTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

		this.ItemEstimativaObservableList = FXCollections.observableArrayList(this.loadItemEstimativa);
		this.tableViewCadastroItemEstimativa.setItems(this.ItemEstimativaObservableList);
		this.tableViewCadastroItemEstimativa.refresh();
	}
	
	@FXML
	public void handleButtonDeletar() throws IOException {
		if (loadItemEstimativa.isEmpty()) {
			//this.label2.setText("Você não tem tarefas cadastradas!");
		}
		else {
			this.itemEstimativa = this.tableViewCadastroItemEstimativa.getSelectionModel().getSelectedItem();
			Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
			confirmationAlert.setHeaderText("Remoção de item estimatido");
			confirmationAlert.setContentText("Deseja realmente apagar o item estimado?");
			
			Optional<ButtonType> result = confirmationAlert.showAndWait();
			if (result.get() == ButtonType.OK) {
				ItemEstimativaDAO itemEstimativaDAO = new ItemEstimativaDAO();
				itemEstimativaDAO.delete(this.itemEstimativa);
				listaItemEstimativa();
			}
		}
	}
	
	@FXML
	public void handleButtonVoltar() throws IOException {
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/estimativa.fxml"));
        this.aPane.getChildren().setAll(ap);
	}
	
	@FXML
	public void handleGravar() throws IOException {		
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/estimativa.fxml"));
        this.aPane.getChildren().setAll(ap);
	}
	
	
	
	
}
