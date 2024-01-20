package Main.Chat;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author USUARIO
 */
public class FXMLDocumentController  {
    
    @FXML
    private Button btnEnviar;
    @FXML
    private TextField Mensaje;
    @FXML
    private TextArea mensajes;
    
    @FXML
    
    private void handleButtonAction(ActionEvent event) {
        btnEnviar.setDisable(true);
        mensajes.appendText(Mensaje.getText()+"\n");
        Mensaje.clear();
        btnEnviar.setDisable(false);
    }   
}
