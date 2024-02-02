package Main.Chat;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDateTime;
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
public class FXMLDocumentController  {
    private int puertoSocketController = 5555;
    
    
    Socket socketCliente = null;
    BufferedReader entradaSocket = null;
    PrintWriter salidaSocket = null;
    ReadSocketMessages rs;
    
    
    
    
    @FXML
    private Button btnEnviar;
    @FXML
    private TextField Mensaje;
    @FXML
    private TextArea mensajes;

    public FXMLDocumentController() {
        /*this.rs = null;
        this.numero = 0;*/
        
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
        
        
        
        String mensaje = Mensaje.getText();
        
        /*if(server.enviarTxt(mensaje)){
            System.out.println(server.enviarTxt(mensaje));
            mensajes.appendText(getTime() + "-> "+ mensaje);
            Mensaje.clear();
        }*/
        
        
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
    
    public void configClient(String url, int puerto) {
        try {
            
            socketCliente = new Socket(url, puerto);
            salidaSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
   


    
    public void configServerDefault(int puerto) throws IOException {
        
        // Configurar el servidor
        try (Socket socketServer = new Socket("localhost", 5555);
            PrintWriter salidaServer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketServer.getOutputStream())), true)) {
            salidaServer.println("puerto:" + puerto);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
          
    
    private String getTime(){
        LocalDateTime local = LocalDateTime.now();
        
        return "[" +local.getHour() + ":" +local.getMinute()+":"+local.getSecond()+"]";
    }

    private void backMain() {
        try {
            
            onCloseRequest();
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
    
    public void onCloseRequest() {
        try {
            socketCliente.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
