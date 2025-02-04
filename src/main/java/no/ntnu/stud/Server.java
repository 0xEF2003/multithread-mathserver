package no.ntnu.stud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {
  private int port;
  private boolean running = true;

  public Server(int port) {
    this.port = port;
  }


  /**
   * Runs the server.
   *
   * <p>Waits for clients and listens to commands.
   */
  public void run(boolean multiThreaded) {
    ServerSocket server;

    try { // Create server socket
      server = new ServerSocket(this.port);
      while (running) { // Handle clients
        Socket client = server.accept();
        if (multiThreaded) {
          new Thread(() -> {
            try {
              handleClient(client);
            } catch (IOException e) {
              System.out.println(e.getMessage());
            }
          }).start();
        } else {
          handleClient(client);
        }
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }


  }

  public void close() {
    this.running = false;
  }


  private void handleClient(Socket client) throws IOException {
    try (PrintWriter out = new PrintWriter(client.getOutputStream(), true);
         BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
      String line;
      while ((line = in.readLine()) != null && !line.equals("exit")) {
        Request request = null;
        double result = 0;
        String message = "";

        // Parse request
        try {
          request = new Request(line);
        } catch (Exception e) {
          message = "Bad request";
        }

        try { // Do math operation
          result = MathHandler.perform(request);
          message = String.valueOf(result);
        } catch (Exception e) {
          message = "Bad math";
          System.out.println(e.getMessage());
        }

        // Send result back to client
        out.println(message);
      }
    }
  }
}

