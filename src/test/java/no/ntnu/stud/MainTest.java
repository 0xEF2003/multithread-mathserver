package no.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


import java.net.Socket;

import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Test;

import no.ntnu.stud.client.Client;

class MainTest {
    
    @Test
    void testAddition() {
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

    @Test
    void testSubtraction() {
        int port = 1235;
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
                System.out.println(client.run("S 6 2"));
                assertEquals("4.0", client.run("S 6 2"));
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

    @Test
    void testMultiplication() {
        int port = 1236;
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
                System.out.println(client.run("M 2 2"));
                assertEquals("4.0", client.run("M 2 2"));
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

    @Test
    void testDivision() {
        int port = 1237;
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
                System.out.println(client.run("D 8 2"));
                assertEquals("4.0", client.run("D 8 2"));
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

    @Test
    void multiThreadedTest() {
        int port = 1238;
        boolean multithreading = true;
        int amountOfClients = 10;
        Server server = new Server(port);

        new Thread(() -> {
            assertDoesNotThrow(() -> {
                server.run(multithreading);
            });
        }).start();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < amountOfClients; i++) {
                assertDoesNotThrow(() -> {
                    Client client;
                    Socket socket = new Socket("localhost", port);
                    client = new Client(socket);
                    System.out.println(client.run("A 2 2"));
                    assertEquals("4.0", client.run("A 2 2"));
                    client.close();
                });
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Multithreaded test took: " + (endTime - startTime) + " ms");
    }

    @Test
    void singleThreadTest() {
        int port = 1239;
        boolean multithreading = false;
        int amountOfClients = 10;
        Server server = new Server(port);

        new Thread(() -> {
            assertDoesNotThrow(() -> {
                server.run(multithreading);
            });
        }).start();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < amountOfClients; i++) {
                assertDoesNotThrow(() -> {
                    Client client;
                    Socket socket = new Socket("localhost", port);
                    client = new Client(socket);
                    System.out.println(client.run("A 2 2"));
                    assertEquals("4.0", client.run("A 2 2"));
                    client.disconnect();
                });
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Single-threaded test took: " + (endTime - startTime) + " ms");
    }
}
