package no.ntnu.stud;


public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");
        Server server = new Server(1234);
        server.run();
    }
}
