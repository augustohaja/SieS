package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import models.Estimativa;

public class EstimativaController implements Initializable {

	@FXML
	private AnchorPane aPane;
	@FXML
	private Button btnInsere;
	@FXML
	private Button btnVerEstimativa;
	@FXML
	private Button btnSair;
	@FXML
	private Button btnDeleta;
	
	private ObservableList<Estimativa> materialObservableList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	

}
