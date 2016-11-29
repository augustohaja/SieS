
package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.CategoriaDAO;
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
import models.CategoriaDireto;

public class CadastroCategoriaController implements Initializable {	
	@FXML
	private AnchorPane aPane;
	@FXML
	private TableView<Categoria> tableViewCategorias;
	@FXML
	private TableColumn<Categoria,Long> idCategoria;
	@FXML
	private TableColumn<Categoria,String> descricaoCategoria;
	@FXML
	private TableColumn<Categoria,String> tipoCategoria;
	@FXML
	private Button buttonCancela;
	@FXML
	private Button buttonIncluir;
	@FXML
	private Button buttonEditar;
	@FXML
	private Button buttonExcluir;

	private List<Categoria> categoriaList;
	private ObservableList<Categoria> categoriaObservableList;
	private CategoriaDAO dao;
	private Categoria categoria;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	System.out.println("Inicializando...");
		this.dao = new CategoriaDAO();
		loadTableViewCategorias();
		
		// Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        this.tableViewCategorias.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectItemTableViewCategorias(newValue));
    }
    
    private void loadTableViewCategorias() {
		this.categoriaList = this.dao.all();
		
		// a string é o nome do atributo da classe do objeto
		this.idCategoria.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.descricaoCategoria.setCellValueFactory(new PropertyValueFactory<>("nome"));
		this.tipoCategoria.setCellValueFactory(new PropertyValueFactory<>("tipoConsumo"));
		
		// conversão de ArrayList para ObservableList
		this.categoriaObservableList = FXCollections.observableArrayList(this.categoriaList);
		this.tableViewCategorias.setItems(this.categoriaObservableList);
	}
    
	public void selectItemTableViewCategorias(Categoria categoria) {
		System.out.println(categoria);
		this.categoria = categoria;
	}
    
	@FXML
	public void handleButtonIncluir() throws IOException {
		//Categoria categoria = new CategoriaDireto();
		boolean buttonConfirmarClicked = showAnchorPaneCadastroCategoriaDialog(0);
		
		if (buttonConfirmarClicked) {
		//	this.dao.insert(categoria);
			loadTableViewCategorias();
		}
	}
	
	@FXML
	public void handleButtonEditar() throws IOException {
		Categoria categoria = this.tableViewCategorias.getSelectionModel().getSelectedItem();
		
		if (categoria == null) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setContentText("Por favor, escolha uma categoria na Tabela!");
			errorAlert.show();
		}
		else {
			boolean buttonConfirmarClicked = showAnchorPaneCadastroCategoriaDialog(1);
			
			if (buttonConfirmarClicked) {
				this.dao.update(categoria);
				loadTableViewCategorias();
			}
		}
		this.tableViewCategorias.refresh();
	}

	
	@FXML
	public void handleButtonExcluir() {
		Categoria categoria = this.tableViewCategorias.getSelectionModel().getSelectedItem();
		
		if (categoria == null) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setContentText("Por favor, escolha uma categoria na Tabela!");
			errorAlert.show();
		}
		else {
			Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
			confirmationAlert.setHeaderText("Remo��o da Categoria");
			confirmationAlert.setContentText("Deseja realmente apagar a categoria?");
			
			Optional<ButtonType> result = confirmationAlert.showAndWait();
			if (result.get() == ButtonType.OK) {
				this.dao.delete(categoria);
				loadTableViewCategorias();
			}
		}
	}

	public boolean showAnchorPaneCadastroCategoriaDialog(Integer opc) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CadastroCategoriaDialogController.class.getResource("/views/CadastroCategoriaDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cadastro de Tarefas");
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		
		CadastroCategoriaDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		
		if (opc == 1)
			controller.setCategoria(categoria);
		
		controller.setOpc(opc);
		
		
		dialogStage.showAndWait();
		
		return controller.isButtonConfirmarClicked();
	}

	
	@FXML
	public void handleButtonCancel() throws IOException {
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/menu.fxml"));
        this.aPane.getChildren().setAll(ap);
	}
	
}
