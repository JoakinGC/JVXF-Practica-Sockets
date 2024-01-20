package Main;


import Main.Chat.FXMLDocumentController;
import Main.Client.ClientConfigFXMLController;
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

public class InicioSessionFXMLController implements Initializable {

    @FXML
    private Button btnViewServer;
    
    @FXML
    private Button btnViewClient;
    
    @FXML
    private Button btnViewChat;
   
    @FXML
    private void openViewChat() {        
        try {
            Stage stage = (Stage) btnViewChat.getScene().getWindow();
            stage.close(); 

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main/Chat/FXMLDocument.fxml"));
            Parent root = loader.load();

            FXMLDocumentController controller = loader.getController();
          
            Scene scene = new Scene(root);
            stage = new Stage();            
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void openViewCleint() {        
        try {
            Stage stage = (Stage) btnViewClient.getScene().getWindow();
            stage.close(); 

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main/Client/ClientConfigFXML.fxml"));
            Parent root = loader.load();

            ClientConfigFXMLController controller = loader.getController();
          
            Scene scene = new Scene(root);
            stage = new Stage();            
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void openViewServer() {        
        try {
            Stage stage = (Stage) btnViewServer.getScene().getWindow();
            stage.close(); 

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main/Server/ConfigServerFXML.fxml"));
            Parent root = loader.load();

            ConfigServerFXMLController controller = loader.getController();
          
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
    }
    
}
