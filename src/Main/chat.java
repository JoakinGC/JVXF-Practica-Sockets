package Main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */


import Main.Chat.FXMLDocumentController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author USUARIO
 */
public class chat extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try {                   
                Parent root = FXMLLoader.load(getClass().getResource("InicioSessionFXML.fxml"));
                
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                 e.printStackTrace();
            }

    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
