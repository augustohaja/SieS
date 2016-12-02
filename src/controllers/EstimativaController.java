package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.EstimativaDAO;
import dao.MaterialDAO;
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
import models.Estimativa;

public class EstimativaController implements Initializable {

	@FXML
	private AnchorPane aPane;
	@FXML
	private Button btnInsere;
	@FXML
	private Button btnVisualizar;
	@FXML
	private Button btnSair;
	@FXML
	private Button btnDeleta;
	@FXML
	private TableView<Estimativa> tableViewEstimativa;
	@FXML
	private TableColumn<Estimativa, String> cData;
	@FXML
	private TableColumn<Estimativa, String> cNome;
	
	private ObservableList<Estimativa> estimativaObservableList;
	
	private List<Estimativa> loadEstimativa;
	
	private Estimativa estimativa;

	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	listaMateriais();
        this.tableViewEstimativa.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectItemTableViewEstimativa(newValue));
    }
	
	public void selectItemTableViewEstimativa(Estimativa estimativaSelected){
		this.estimativa = estimativaSelected;
	}
	
	public void handleButtonInserirEstimativa() throws IOException {
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/cadastroItemEstimativa.fxml"));
        this.aPane.getChildren().setAll(ap);
        //this.tableViewEstimativa.refresh();
	}
	
	private void listaMateriais() {
		EstimativaDAO estimativaDAO = new EstimativaDAO();
		this.loadEstimativa = estimativaDAO.all();
		
		this.cData.setCellValueFactory(new PropertyValueFactory<>("data"));
		this.cNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		this.estimativaObservableList = FXCollections.observableArrayList(this.loadEstimativa);
		this.tableViewEstimativa.setItems(this.estimativaObservableList);
		this.tableViewEstimativa.refresh();
	}
	
	@FXML
	public void handleButtonVoltar() throws IOException {
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/menu.fxml"));
        this.aPane.getChildren().setAll(ap);
	}
	
	@FXML
	public void handleButtonDeletar() throws IOException {
		if (loadEstimativa.isEmpty()) {
			//this.label2.setText("Você não tem tarefas cadastradas!");
		}
		else {
			this.estimativa = this.tableViewEstimativa.getSelectionModel().getSelectedItem();
			Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
			confirmationAlert.setHeaderText("Remoção de estimativa");
			confirmationAlert.setContentText("Deseja realmente apagar a estimativa?");
			
			Optional<ButtonType> result = confirmationAlert.showAndWait();
			if (result.get() == ButtonType.OK) {
				EstimativaDAO estimativaDAO = new EstimativaDAO();
				estimativaDAO.delete(this.estimativa);
				listaMateriais();
			}
		}
	}

}
