package Main.Chat;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */


import Main.InicioSessionFXMLController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author USUARIO
 */
public class FXMLDocumentController  implements Initializable{
     Socket socketCliente = null;
     BufferedReader entradaSocket = null;
     PrintWriter salidaSocket = null;
     ReadSocketMessages rs;
     int numero = 0;
    
    @FXML
    private Button btnEnviar;
    @FXML
    private TextField Mensaje;
    @FXML
    private TextArea mensajes;

    public FXMLDocumentController(int numero) {
        this.rs = null;
        this.numero = numero;
    }
    
    public void updateMessages() throws IOException{
        entradaSocket = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));        
        rs = new ReadSocketMessages(entradaSocket,this);	
    
	rs.start();
    }
    
    @FXML
    private void handleButtonAction() throws IOException {
        updateMessages();
        btnEnviar.setDisable(true);
        
        String lineaServidor, lineaTeclado;
        
        if(Mensaje.getText().equals("Adios")){
            salidaSocket.println(Mensaje.getText());
            salidaSocket.close();
            socketCliente.close();
            backMain();
        }else{
            salidaSocket.println(Mensaje.getText());
        }                        
        Mensaje.clear();
        btnEnviar.setDisable(false);
    }   
   
    public  void agregarRespuesta(String l){        
        mensajes.appendText("\n"+l);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
	      socketCliente = new Socket("localhost", 4444);	      
	      salidaSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);		      	      	      	      
              this.agregarRespuesta("Cliente " + numero);
	    } catch (IOException e){
                System.err.println("\"No puede establer canales de E/S para la conexi�n\"");
		mensajes.appendText("No puede establer canales de E/S para la conexi�n");
	        System.exit(-1);
	    }
    }

    private void backMain() {
        try {
            Stage stage = (Stage) btnEnviar.getScene().getWindow();
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
    
}
