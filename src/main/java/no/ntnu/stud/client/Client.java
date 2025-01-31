package no.ntnu.stud.client;

import java.io.IOException;
import java.net.Socket;

public class Client {
  private final ClientConnectionHandler connectionHandler;

    public Client(Socket socket) throws IOException {
        this.connectionHandler = new ClientConnectionHandler(socket);
    }

    public void start() {
        while (true) {
          connectionHandler.handleUserInput();
        }
    }

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1250);
            Client client = new Client(socket);
            client.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}