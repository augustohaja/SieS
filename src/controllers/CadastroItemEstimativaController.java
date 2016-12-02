package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.CategoriaDAO;
import dao.EstimativaDAO;
import dao.ItemEstimativaDAO;
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
import models.Categoria;
import models.Estimativa;
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
	
	private List<ItemEstimativa> itemEstimativaList;
	
	private ItemEstimativa itemEstimativa;
	
	private ItemEstimativaDAO itemEstimativaDAO;
	
	private Estimativa estimativa = new Estimativa();
	
	private ItemEstimativaDAO dao;
	
	private EstimativaDAO daoEstimativa;

	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	System.out.println("Inicializando...");
		this.dao = new ItemEstimativaDAO();
		this.daoEstimativa = new EstimativaDAO();
		loadTableViewItemEstimativas();
		
		// Listen acionado diante de quaisquer alteraÃ§Ãµes na seleÃ§Ã£o de itens do TableView
        this.tableViewCadastroItemEstimativa.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectItemTableViewItemEstimativa(newValue));
    }
	
	private void loadTableViewItemEstimativas() {
		this.itemEstimativaList = this.dao.all();
		
		// a string Ã© o nome do atributo da classe do objeto
		this.cId.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.cDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		this.cQtdem2.setCellValueFactory(new PropertyValueFactory<>("qtd"));
		this.cEstimado.setCellValueFactory(new PropertyValueFactory<>("estimado"));
		this.cVlrTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		
		// conversÃ£o de ArrayList para ObservableList
		this.ItemEstimativaObservableList = FXCollections.observableArrayList(this.itemEstimativaList);
		this.tableViewCadastroItemEstimativa.setItems(this.ItemEstimativaObservableList);
	}
	
	public void selectItemTableViewItemEstimativa(ItemEstimativa itemEstimativa) {
		System.out.println(itemEstimativa);
		this.itemEstimativa = itemEstimativa;
	}
	
	@FXML
	public void handleButtonIncluir() throws IOException {
		//Categoria categoria = new CategoriaDireto();
		boolean buttonConfirmarClicked = showAnchorPaneCadastroEstimativaDialog(0);
		
		if (buttonConfirmarClicked) {
		//	this.dao.insert(categoria);
			loadTableViewItemEstimativas();
		}
	}
	
	@FXML
	public void handleButtonExcluir() {
		ItemEstimativa ItemEstimativa = this.tableViewCadastroItemEstimativa.getSelectionModel().getSelectedItem();
		
		if (estimativa == null) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setContentText("Por favor, escolha um novo item estimado na Tabela!");
			errorAlert.show();
		}
		else {
			Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
			confirmationAlert.setHeaderText("Remoção do item estimado!");
			confirmationAlert.setContentText("Deseja realmente apagar o item estimado?");
			
			Optional<ButtonType> result = confirmationAlert.showAndWait();
			if (result.get() == ButtonType.OK) {
				this.dao.delete(itemEstimativa);
				loadTableViewItemEstimativas();
			}
		}
	}
	
	public boolean showAnchorPaneCadastroEstimativaDialog(Integer opc) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(InserirItemEstimativaController.class.getResource("/views/inserirItemEstimativa.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cadastro Item Estimativa");
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		
		InserirItemEstimativaController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		
		if (opc == 1)
			controller.setEstimativa(estimativa);
				
		dialogStage.showAndWait();
		
		return controller.isButtonConfirmarClicked();
	}
	
	@FXML
	public void handleButtonVoltar() throws IOException {
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/estimativa.fxml"));
        this.aPane.getChildren().setAll(ap);
	}
	
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
////		estimativa = new Estimativa();
//		listaItemEstimativa();
//        this.tableViewCadastroItemEstimativa.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> selectItemTableViewItemEstimativa(newValue));
//	}
//	
//	public void selectItemTableViewItemEstimativa(ItemEstimativa itemEstimativa){
//		this.itemEstimativa = itemEstimativa;
//	}
//	
//	@FXML
//	public void handleButtonInserirItem() throws IOException {
//		
//		boolean buttonConfirmarClicked = showAnchorPaneCadastroCategoriaDialog(0);
//		
//		if (buttonConfirmarClicked) {
//			loadTableViewCategorias();
//		}
//		
//		
////		FXMLLoader loader = new FXMLLoader();
////		loader.setLocation(CadastroItemEstimativaController.class.getResource("/views/cadastroItemEstimativa.fxml"));
////		
////		InserirItemEstimativaController controller = loader.getController();
////		controller.setEstimativa(estimativa);
////		
////		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/inserirItemEstimativa.fxml"));
////        this.aPane.getChildren().setAll(ap);
////        
////        this.tableViewCadastroItemEstimativa.refresh();
//	}
//
//	private boolean showAnchorPaneCadastroCategoriaDialog(int i) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	private void listaItemEstimativa() {
//		ItemEstimativaDAO itemEstimativaDAO = new ItemEstimativaDAO();
//		this.loadItemEstimativa = itemEstimativaDAO.all();
//		
//		this.cId.setCellValueFactory(new PropertyValueFactory<>("id"));
//		this.cDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
//		this.cQtdem2.setCellValueFactory(new PropertyValueFactory<>("qtd"));
//		this.cEstimado.setCellValueFactory(new PropertyValueFactory<>("estimado"));
//		this.cVlrTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
//
//		this.ItemEstimativaObservableList = FXCollections.observableArrayList(this.loadItemEstimativa);
//		this.tableViewCadastroItemEstimativa.setItems(this.ItemEstimativaObservableList);
//		this.tableViewCadastroItemEstimativa.refresh();
//	}
//	
//	@FXML
//	public void handleButtonDeletar() throws IOException {
//		if (loadItemEstimativa.isEmpty()) {
//			//this.label2.setText("Você não tem tarefas cadastradas!");
//		}
//		else {
//			this.itemEstimativa = this.tableViewCadastroItemEstimativa.getSelectionModel().getSelectedItem();
//			Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
//			confirmationAlert.setHeaderText("Remoção de item estimatido");
//			confirmationAlert.setContentText("Deseja realmente apagar o item estimado?");
//			
//			Optional<ButtonType> result = confirmationAlert.showAndWait();
//			if (result.get() == ButtonType.OK) {
//				ItemEstimativaDAO itemEstimativaDAO = new ItemEstimativaDAO();
//				itemEstimativaDAO.delete(this.itemEstimativa);
//				listaItemEstimativa();
//			}
//		}
//	}
//	
//	@FXML
//	public void handleButtonVoltar() throws IOException {
//		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/estimativa.fxml"));
//        this.aPane.getChildren().setAll(ap);
//	}
//	
//	@FXML
//	public void handleButtonGravar() throws IOException {		
//		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/estimativa.fxml"));
//        this.aPane.getChildren().setAll(ap);
//	}
//	
//	
//	
	
}
