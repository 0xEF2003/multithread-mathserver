package no.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.net.Socket;

import org.junit.jupiter.api.Test;

import no.ntnu.stud.client.Client;

public class MainTest {
    
    @Test
    public void testAddition() {
        int port = 1234;
        boolean multithreading = false;
        Server server = new Server(port);

        new Thread(() -> {
            assertDoesNotThrow(() -> {
            server.run(multithreading);
            });
        }).start();

        new Thread(() -> {
            assertDoesNotThrow(() -> {
                Client client;
                Socket socket = new Socket("localhost", port);
                client = new Client(socket);
                client.run("A 2 2");
            });
        }).start();
    }

}
