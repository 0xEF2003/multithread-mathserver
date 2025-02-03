package no.ntnu.stud.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientConnectionHandler {
  private final PrintWriter outputWriter;
  private final Socket socket;
  protected BufferedReader inputReader;

  public ClientConnectionHandler(Socket socket) throws IOException {
    this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    this.outputWriter = new PrintWriter(socket.getOutputStream(), true);
    this.socket = socket;
  }

public void sendMessage(String message) {
    this.outputWriter.println(message);
  }

  public String getMessage() {
    try {
      return this.inputReader.readLine();
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public void close() {
    try {
      this.inputReader.close();
      this.outputWriter.close();
      this.socket.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public String sendUserInput(String userInput) {
    sendMessage(userInput);
    String response = getMessage();
    return response;
  }

  public String getUserInput(){
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      return reader.readLine();
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public boolean handleUserInput() {
    System.out.println("Enter a message to send to the server in format of 'Operation(A/S/M/D) Number Number'");
    String userInput = getUserInput();
    if (userInput == null) {
      return false;
    }

    System.out.println(sendUserInput(userInput));
    return true;
  }

}