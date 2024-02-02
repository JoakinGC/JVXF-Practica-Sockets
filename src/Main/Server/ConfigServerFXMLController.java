package Main.Server;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


import Main.Chat.FXMLDocumentController;
import Main.InicioSessionFXMLController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class ConfigServerFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
        
    @FXML
    private Button btnViewChat;
    
    @FXML
    private Button btnViewMain;
    @FXML
    private TextField puerto;
    
    @FXML
    private void viewChat() {
        try {
            Stage stage = (Stage) btnViewChat.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main/Chat/FXMLDocument.fxml"));
            
            Parent root = loader.load();
            FXMLDocumentController controller = loader.getController();
            
            try {
                int num = Integer.parseInt(puerto.getText());
                controller.configServerDefault(num);
                controller.configClient("localhost", num);
        
            } catch (NumberFormatException e) {
       
                System.out.println("Invalid input. Please enter a valid integer.");
            }
           
            
            

            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void backMain(){
        try {
            Stage stage = (Stage) btnViewChat.getScene().getWindow();
            stage.close(); 

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main/InicioSessionFXML.fxml"));
            Parent root = loader.load();

            InicioSessionFXMLController controller = loader.getController();
          
            Scene scene = new Scene(root);
            stage = new Stage();            
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
