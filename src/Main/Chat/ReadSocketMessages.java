/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Main.Chat;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author USUARIO
 */
public class ReadSocketMessages extends Thread{
    
    BufferedReader entradaSocket = null;
    FXMLDocumentController ct = null;
		ReadSocketMessages(BufferedReader br,FXMLDocumentController ct){
			this.entradaSocket=br;			
                        this.ct = ct;
		}
		
		
		@Override
		public void run() {
			try {
				
				String byeMessage="__Adios__&%#";
                                String lineaServidor;
				while(true){	    
					lineaServidor = entradaSocket.readLine();
                                        
					if(lineaServidor.equals(byeMessage))
						break;
					
		        	//System.out.println("\nRespuesta servidor: '" + lineaServidor+"'");
                                    ct.agregarRespuesta(lineaServidor);
		        }

				entradaSocket.close();
				
		} catch (IOException e) {
						
						e.printStackTrace();
					}
		}
}
    

