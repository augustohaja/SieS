
package controllers;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;

import dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Usuario;
import models.UsuarioSessao;
import tools.EmailValidator;
import tools.SHAUtil;
import tools.ToolMessage;


public class LoginController implements Initializable {
	@FXML
	private Button buttonCadastrar;
	@FXML
	private Button buttonLogin;
	@FXML
	private TextField txtNomeCadastro;
	@FXML
	private TextField txtEmailCadastro;
	@FXML
	private PasswordField txtSenhaCadastro;
	@FXML
	private TextField txtEmailLogin;
	@FXML
	private PasswordField txtSenhaLogin;
	@FXML
	private AnchorPane aPane;
	
	private UsuarioDAO usuarioDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.usuarioDAO = new UsuarioDAO();
    }    
   
	
	@FXML
	public void handleButtonCadastrar() throws IOException {
		 Usuario usuario = new Usuario();
				
		if (!txtNomeCadastro.getText().equals("") && !txtEmailCadastro.getText().equals("") && !txtSenhaCadastro.getText().equals("")){
			
			usuario.setNome(this.txtNomeCadastro.getText());			
			EmailValidator valida = new EmailValidator();
			
			if(valida.validate(this.txtEmailCadastro.getText())) {
				usuario.setEmail(this.txtEmailCadastro.getText());
				try {
					usuario.setSenha(SHAUtil.geraHash(this.txtSenhaCadastro.getText()));
					
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				
				this.usuarioDAO.insert(usuario);
				ToolMessage.showInformationMessage("","Cadastro realizado com sucesso!");
				limpaCamposCadastro();
			} else {
				ToolMessage.showInformationMessage("","Não foi possível realizar o seu cadastro");
			}

		} else {
			ToolMessage.showInformationMessage("","Não foi possível realizar o seu cadastro");
		}
	}
	
	@FXML
	public void handleButtonLogin() throws IOException {
		
		if(!this.txtEmailLogin.getText().equals("") && !this.txtSenhaLogin.getText().equals("")){
			List<Usuario> usuario = null;
				usuario = this.usuarioDAO.searchByEmail(this.txtEmailLogin.getText());
			if (usuario.size() > 0) {
				System.out.println("--"+usuario);
				try {
					if (SHAUtil.geraHash(this.txtSenhaLogin.getText()).equals(usuario.get(0).getSenha()) ){
						UsuarioSessao.setUser(usuario.get(0));
						
						showNextScreen();
					
					} else {
						ToolMessage.showErrorMessage("Verifique usuário e senha.");
					}
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			} else {
				ToolMessage.showErrorMessage("Não foi possível realizar o login");
			}
		} else {
			ToolMessage.showErrorMessage("Não foi possível realizar o login");
		}
	}
	
	public void limpaCamposLogin(){
		this.txtEmailLogin.setText("");
		this.txtEmailLogin.setText("");
	}
	
	public void limpaCamposCadastro(){
		this.txtNomeCadastro.setText("");
		this.txtEmailCadastro.setText("");
		this.txtSenhaCadastro.setText("");
	}
    
	
	public void showNextScreen() throws IOException {
		

		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/menu.fxml"));
        this.aPane.getChildren().setAll(ap);
	}
}
