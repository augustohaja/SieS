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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Categoria;
import models.Estimativa;
import models.ItemEstimativa;
import models.Sessao;
import tools.ToolMessage;

public class VisualizarEstimativaController implements Initializable  {

	@FXML
	private AnchorPane aPane;
	@FXML
	private Label lblNome;
	@FXML
	private Label lblData;
	@FXML
	private Button btnInsere;
	@FXML
	private Button btnSair;
	@FXML
	private Button btnGravar;
	@FXML
	private Button btnDeleta;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtData;
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
		
	private ObservableList<ItemEstimativa> itemEstimativaObservableList;
	
	private List<ItemEstimativa> itemEstimativaList;
	
	private ItemEstimativa itemEstimativa;
	
	private Estimativa estimativa = new Estimativa();
	
	private EstimativaDAO daoEstimativa = new EstimativaDAO();

	private Stage dialogStage;
	
	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setEstimativa(Estimativa estimativa){
		this.estimativa = Sessao.getEstimativa();
		System.out.println(estimativa.getData());
		System.out.println(estimativa.getId());
	}
	
	public void setDisable(){
		this.txtNome.setText(estimativa.getNome());
		this.txtData.setText(estimativa.getData());
		this.btnInsere.setDisable(true);
		this.btnDeleta.setDisable(true);
		this.btnGravar.setDisable(true);
		this.txtNome.setDisable(true);
		this.txtData.setDisable(true);
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	System.out.println("Inicializando...");
    	this.estimativa = Sessao.getEstimativa();
    	this.lblNome.setText(this.estimativa.getNome());
    	this.lblData.setText(this.estimativa.getData());
    	
		loadTableViewItemEstimativas();
		
		// Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        this.tableViewCadastroItemEstimativa.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectItemTableViewItemEstimativa(newValue));
    }
	
	private void loadTableViewItemEstimativas() {
		if (estimativa.getId() != null){
			System.out.println("Id buscado: " + estimativa.getId());
			this.itemEstimativaList = this.daoEstimativa.searchByEstimativa(estimativa.getId());

			// 	a string é o nome do atributo da classe do objeto
			this.cId.setCellValueFactory(new PropertyValueFactory<>("id"));
			this.cDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
			this.cQtdem2.setCellValueFactory(new PropertyValueFactory<>("qtd"));
			this.cEstimado.setCellValueFactory(new PropertyValueFactory<>("estimado"));
			this.cVlrTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		
			// 	conversão de ArrayList para ObservableList
			this.itemEstimativaObservableList = FXCollections.observableArrayList(this.itemEstimativaList);
			this.tableViewCadastroItemEstimativa.setItems(this.itemEstimativaObservableList);
		}
	}
	
	public void selectItemTableViewItemEstimativa(ItemEstimativa itemEstimativa) {
		System.out.println(itemEstimativa);
		this.itemEstimativa = itemEstimativa;
	}
	
	@FXML
	public void handleButtonIncluir() throws IOException {
		//Categoria categoria = new CategoriaDireto();
		
		if (txtNome.getText().equals("") || (txtData.getText().equals(""))){
			ToolMessage.showInformationMessage("Aviso","Prencha os dados campos Nome e Data da estimativa!");
		} else {
			if (estimativa.getId() == null){
				estimativa.setNome(txtNome.getText());
				estimativa.setData(txtData.getText());
				daoEstimativa.insert(estimativa);
			}
			System.out.println(estimativa.toString());
			this.daoEstimativa.update(estimativa);
			System.out.println(estimativa.toString());
			boolean buttonConfirmarClicked = showAnchorPaneCadastroEstimativaDialog(0);
			
			if (buttonConfirmarClicked) {
				System.out.println(estimativa.toString());
				System.out.println(estimativa.getLista().size());
				this.daoEstimativa.update(estimativa);
			}
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
			confirmationAlert.setHeaderText("Remo��o do item estimado!");
			confirmationAlert.setContentText("Deseja realmente apagar o item estimado?");
			
			Optional<ButtonType> result = confirmationAlert.showAndWait();
			if (result.get() == ButtonType.OK) {
				this.estimativa.removeItemEstimativa(itemEstimativa);
				
				ItemEstimativaDAO daoItem = new ItemEstimativaDAO();
				daoItem.delete(itemEstimativa);
				
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
		
		if (opc == 0)
			controller.setEstimativa(estimativa);
				
		dialogStage.showAndWait();
		this.estimativa = controller.getEstimativa();
		System.out.println(this.estimativa.toString());
		return controller.isButtonConfirmarClicked();
	}
	
	@FXML
	public void handleButtonVoltar() throws IOException {
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/estimativa.fxml"));
        this.aPane.getChildren().setAll(ap);
	}	
}
