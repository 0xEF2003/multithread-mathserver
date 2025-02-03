package no.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


import java.io.IOException;
import java.net.Socket;

import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Test;

import no.ntnu.stud.client.Client;

public class MainTest {
    
    @Test
    public void testAddition() {
        int port = 1234;
        boolean multithreading = false;
        Server server = new Server(port);
        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            assertDoesNotThrow(() -> {
                server.run(multithreading);
                latch.countDown();
            });
        }).start();

        new Thread(() -> {
            assertDoesNotThrow(() -> {
                Client client;
                Socket socket = new Socket("localhost", port);
                client = new Client(socket);
                System.out.println(client.run("A 2 2"));
                 assertEquals("4.0", client.run("A 2 2"));
                latch.countDown();
                client.close();
            });
        }).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
