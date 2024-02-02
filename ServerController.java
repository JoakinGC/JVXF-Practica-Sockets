package ejecicios.ejercicio4;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ServerController {

    private static final int CONTROL_PORT = 5555;
    private static Set<Integer> usedPorts = new HashSet<>();
    
    

    public static void main(String[] args) {
        ControlServer controlServer = new ControlServer(CONTROL_PORT);
        new Thread(controlServer).start();
    }

    static class ControlServer implements Runnable {
        private int controlPort;

        public ControlServer(int controlPort) {
            this.controlPort = controlPort;
        }

        @Override
        public void run() {
            try (ServerSocket controlServerSocket = new ServerSocket(controlPort)) {
                System.out.println("Control Server escuchando en el puerto: " + controlPort);
                while (true) {
                    Socket controlClientSocket = controlServerSocket.accept();
                    ControlHandler controlHandler = new ControlHandler(controlClientSocket);
                    new Thread(controlHandler).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class ControlHandler implements Runnable {
        private Socket controlClientSocket;

        public ControlHandler(Socket controlClientSocket) {
            this.controlClientSocket = controlClientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader controlReader = new BufferedReader(new InputStreamReader(controlClientSocket.getInputStream()))) {
                String request = controlReader.readLine();
                if (request != null && request.startsWith("puerto:")) {
                    String[] parts = request.split(":");
                    if (parts.length == 2) {
                        String portString = parts[1].trim();
                        int chatServerPort = Integer.parseInt(portString);
                        System.out.println("chat server port "+chatServerPort);
                        System.out.println("message: "+ request);
                        if (!usedPorts.contains(chatServerPort)) {
                            usedPorts.add(chatServerPort);
                            ChatServer chatServer = new ChatServer(chatServerPort);
                            System.out.println("ChatServer creado en el puerto: " + chatServerPort);
                        } else if(usedPorts.contains(chatServerPort)) {
                            System.out.println("Ya existe un servidor en el puerto: " + chatServerPort);
                        }else {
                        	System.out.println("Fallo");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    controlClientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
