
package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.MaterialDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Material;

public class MaterialController implements Initializable {	
	@FXML
	private AnchorPane aPane;
	@FXML
	private Button btnInsere;
	@FXML
	private Button btnEdita;
	@FXML
	private Button btnSair;
	@FXML
	private Button btnDeleta;
	@FXML
	private TableView<Material> tableViewMaterial;
	@FXML
	private TableColumn<Material, String> cNome;
	@FXML
	private TableColumn<Material, String> cPreco;
	@FXML
	private TableColumn<Material, String> cQuantidade;
	@FXML
	private TableColumn<Material, String> cUnidade;
	@FXML
	private TableColumn<Material, String> cRendimento;
	@FXML
	private TableColumn<Material, String> cCategoria;
	
	private ObservableList<Material> materialObservableList;
	
	private List<Material> loadMaterial;
	
	private Material material;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	listaMateriais();
        this.tableViewMaterial.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectItemTableViewMaterial(newValue));
    }
    
	@FXML
	public void handleButtonCadastroMaterial() throws IOException {
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/cadastroMaterial.fxml"));
        this.aPane.getChildren().setAll(ap);
        this.tableViewMaterial.refresh();
	}
	
	private void listaMateriais() {
		MaterialDAO materialDAO = new MaterialDAO();
		this.loadMaterial = materialDAO.all();
		
		this.cNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		this.cPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		this.cQuantidade.setCellValueFactory(new PropertyValueFactory<>("qtdEmbalagem"));
		this.cUnidade.setCellValueFactory(new PropertyValueFactory<>("undMedida"));
		this.cRendimento.setCellValueFactory(new PropertyValueFactory<>("coefM2"));
		this.cCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

		this.materialObservableList = FXCollections.observableArrayList(this.loadMaterial);
		this.tableViewMaterial.setItems(this.materialObservableList);
		this.tableViewMaterial.refresh();
	}
	
	public void selectItemTableViewMaterial(Material materialSelected){
		this.material = materialSelected;
	}
	
	
	@FXML
	public void handleButtonEditaMaterial() throws IOException {
//		EditaMaterialController edita = new EditaMaterialController();
//		edita.setMaterial(this.material);
//		edita.start();
//		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/editaMaterial.fxml"));
//        this.aPane.getChildren().setAll(ap);
//        
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(EditaMaterialController.class.getResource("/views/editaMaterial.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
				
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Edição de Materiais");
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		
		
		EditaMaterialController edita = loader.getController();
		edita.setDialogStage(dialogStage);
		
		edita.setMaterial(this.material);
		edita.start();
		
		dialogStage.show();	
	}
	
	@FXML
	public void handleButtonVoltar() throws IOException {
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/menu.fxml"));
        this.aPane.getChildren().setAll(ap);
	}
	
	@FXML
	public void handleButtonDeletar() throws IOException {
		if (loadMaterial.isEmpty()) {
			//this.label2.setText("Você não tem tarefas cadastradas!");
		}
		else {
			this.material = this.tableViewMaterial.getSelectionModel().getSelectedItem();
			Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
			confirmationAlert.setHeaderText("Remoção de tarefas");
			confirmationAlert.setContentText("Deseja realmente apagar o material?");
			
			Optional<ButtonType> result = confirmationAlert.showAndWait();
			if (result.get() == ButtonType.OK) {
				MaterialDAO materialDAO = new MaterialDAO();
				materialDAO.delete(this.material);
				tableViewMaterial.refresh();
			}
		}
	}
}
