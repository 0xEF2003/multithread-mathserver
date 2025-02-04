package no.ntnu.stud;


public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");
        int port = 1234;
        boolean multithreading = false;
        Server server = new Server(port);
        server.run(multithreading);
    }
}
