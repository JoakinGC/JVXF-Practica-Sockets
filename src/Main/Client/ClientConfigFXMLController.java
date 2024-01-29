package Main.Client;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


import Main.Chat.FXMLDocumentController;
import Main.InicioSessionFXMLController;
import Main.Server.ConfigServerFXMLController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class ClientConfigFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnBackMain;
    
    @FXML
    private Button btnViewChat;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void backMainView(){
        try {
            Stage stage = (Stage) btnViewChat.getScene().getWindow();
            stage.close(); 

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main/InicioSessionFXML.fxml"));
            Parent root = loader.load();

        
          
            Scene scene = new Scene(root);
            stage = new Stage();            
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void viewChat(){
         try {
            
            Stage stage = (Stage) btnViewChat.getScene().getWindow();
            stage.close(); 

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main/Chat/FXMLDocument.fxml"));
            Parent root = loader.load();

         
          
            Scene scene = new Scene(root);
            stage = new Stage();            
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
