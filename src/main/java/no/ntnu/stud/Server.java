package no.ntnu.stud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {
  private int port;

  public Server(int port) {
    this.port = port;
  }

  
  /**
   * Runs the server.
   *
   * <p>Waits for clients and listens to commands.
   */
  public void run() {
    try {
      while (true){
        ServerSocket server = new ServerSocket(this.port);
        Socket client = server.accept();
        server.close();
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String line;
        while((line = in.readLine()) != null) {
            Request request = null;
            double result = 0;
            String message = "";

            // Parse request
            try { request = new Request(line); }
            catch (Exception e) { message = "Bad request"; }

            try { // Do math operation
                result = MathHandler.perform(request); 
                message = String.valueOf(result);
            }
            catch (Exception e) {
              message = "Bad math";
              System.out.println(e.getMessage());
            }

            // Send result back to client
            out.println(message);
        }
      }
    }
    catch(IOException e) {
      System.out.println(e);
    }
  }
}

