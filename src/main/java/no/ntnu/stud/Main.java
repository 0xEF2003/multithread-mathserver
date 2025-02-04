package no.ntnu.stud;


public class Main {
  public static void main(String[] args) {
    System.out.println("hello world");
    int port = 1234;
    SingleThreadServer server = new SingleThreadServer(port);
    server.run();
  }
}
