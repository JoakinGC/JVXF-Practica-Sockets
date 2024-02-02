package ejecicios.ejercicio4;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

    private ArrayList<ProcesamientoCliente> clientesConectados;
    private int numberClient = 0;
    private ServerSocket socketServidor;

    public ChatServer(int puerto) {
        clientesConectados = new ArrayList<>();
        try {
            socketServidor = new ServerSocket(puerto);
            System.out.println("Escuchando: " + socketServidor);
            iniciarConexion();
        } catch (IOException e) {
            System.out.println("No puede escuchar en el puerto: " + puerto);
            System.exit(-1);
        }
    }

    private void iniciarConexion() {
        Socket socketCliente = null;
        do {
            try {
                socketCliente = socketServidor.accept();
                ProcesamientoCliente pc = new ProcesamientoCliente(socketCliente);
                pc.start();
                clientesConectados.add(pc);
            } catch (IOException e) {
                System.out.println("Error al aceptar la conexión del cliente: " + e.getMessage());
            }
        } while (true);
    }

    

    public class ProcesamientoCliente extends Thread {
        private Socket socketCliente;
        private PrintWriter salidaSocket;
        private int id = 0;

        public ProcesamientoCliente(Socket s) {
            this.socketCliente = s;
            id= numberClient++;
        }

        public void enviarMensaje(String x) {
            salidaSocket.println(x);
        }

        public void run() {
            BufferedReader entradaSocket = null;
            try {
                System.out.println("Conexión aceptada: " + socketCliente);
                entradaSocket = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                salidaSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
                int aux = 0;
                String mensaje;
                do {
                    mensaje = entradaSocket.readLine();
                    System.out.println("Cliente " + id + ": " + mensaje);
                   

                    if (!mensaje.equals("Adios"))
                        mandarMensajeATodos("Usuario " + id + ":" + mensaje);

                } while (!mensaje.equals("Adios"));
                enviarMensaje("__Adios__&%#");

            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            } finally {
                try {
                    salidaSocket.close();
                    entradaSocket.close();
                    socketCliente.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clientesConectados.remove(this);
            }
        }
    }

    private void mandarMensajeATodos(String x) {
        for (ProcesamientoCliente unClienteConectado : clientesConectados) {
            unClienteConectado.enviarMensaje(x);
        }
    }
}
