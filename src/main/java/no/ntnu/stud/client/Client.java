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

    public String run(String input) {
        return connectionHandler.sendUserInput(input);
    }

    public void disconnect() {
      connectionHandler.close();
    }

    public void close() {
      connectionHandler.sendUserInput("exit");
      connectionHandler.close();
    }

    public static void main(String[] args) {
        try {
          Socket socket = new Socket("localhost", 1234);
          Client client = new Client(socket);
          client.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
