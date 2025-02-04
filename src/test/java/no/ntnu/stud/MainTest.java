package no.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


import java.net.Socket;


import org.junit.jupiter.api.Test;

import no.ntnu.stud.client.Client;

class MainTest {

  @Test
  void testAddition() throws InterruptedException {
    int port = 1234;
    boolean multithreading = false;
    Server server = new Server(port);


    new Thread(() -> {
      assertDoesNotThrow(() -> {
        server.run(multithreading);
      });
    }).start();

    Thread.sleep(10);

    assertDoesNotThrow(() -> {
      Client client;
      Socket socket = new Socket("localhost", port);
      client = new Client(socket);
      System.out.println(client.run("A 2 2"));
      assertEquals("4.0", client.run("A 2 2"));
      client.close();
    });
  }

  @Test
  void testSubtraction() throws InterruptedException {
    int port = 1235;
    boolean multithreading = false;
    Server server = new Server(port);

    new Thread(() -> {
      assertDoesNotThrow(() -> {
        server.run(multithreading);
      });
    }).start();

    Thread.sleep(10);

    assertDoesNotThrow(() -> {
      Client client;
      Socket socket = new Socket("localhost", port);
      client = new Client(socket);
      System.out.println(client.run("S 6 2"));
      assertEquals("4.0", client.run("S 6 2"));
      client.close();
    });
  }

  @Test
  void testMultiplication() throws InterruptedException {
    int port = 1236;
    boolean multithreading = false;
    Server server = new Server(port);

    new Thread(() -> {
      assertDoesNotThrow(() -> {
        server.run(multithreading);
      });
    }).start();

    Thread.sleep(10);

    assertDoesNotThrow(() -> {
      Client client;
      Socket socket = new Socket("localhost", port);
      client = new Client(socket);
      System.out.println(client.run("M 2 2"));
      assertEquals("4.0", client.run("M 2 2"));
      client.close();
    });

  }

  @Test
  void testDivision() throws InterruptedException {
    int port = 1237;
    boolean multithreading = false;
    Server server = new Server(port);

    new Thread(() -> {
      assertDoesNotThrow(() -> {
        server.run(multithreading);
      });
    }).start();

    Thread.sleep(10);

    assertDoesNotThrow(() -> {
      Client client;
      Socket socket = new Socket("localhost", port);
      client = new Client(socket);
      System.out.println(client.run("D 8 2"));
      assertEquals("4.0", client.run("D 8 2"));
      client.close();
    });
  }

  @Test
  void multiThreadedTest() throws InterruptedException {
    int port = 1238;
    boolean multithreading = true;
    int amountOfClients = 10;
    Server server = new Server(port);

    new Thread(() -> {
      assertDoesNotThrow(() -> {
        server.run(multithreading);
      });
    }).start();

    Thread.sleep(10);

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
  void singleThreadTest() throws InterruptedException {
    int port = 1239;
    boolean multithreading = false;
    int amountOfClients = 10;
    Server server = new Server(port);

    new Thread(() -> {
      assertDoesNotThrow(() -> {
        server.run(multithreading);
      });
    }).start();

    Thread.sleep(10);

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
