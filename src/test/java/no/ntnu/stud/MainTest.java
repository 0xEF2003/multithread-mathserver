package no.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


import java.net.Socket;


import java.util.concurrent.CountDownLatch;
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
  void multiThreadedTest() {
    int port = 1238;
    boolean multithreading = true;
    int amountOfClients = 10;
    serverTestWithClients(port, amountOfClients, multithreading);

  }

  @Test
  void singleThreadTest() {
    int port = 1239;
    boolean multithreading = false;
    int amountOfClients = 10;
    serverTestWithClients(port, amountOfClients, multithreading);
  }

  void serverTestWithClients(int port, int amountOfClients, boolean multithreading) {
    Server server = new Server(port);

    new Thread(() -> {
      assertDoesNotThrow(() -> {
        server.run(multithreading);
      });
    }).start();

    // Wait for server to start
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    CountDownLatch latch = new CountDownLatch(amountOfClients-1);
    long startTime = System.currentTimeMillis();

    for (int i = 0; i < amountOfClients; i++) {
      new Thread(() -> {
          assertDoesNotThrow(() -> {
            Client client;
            Socket socket = new Socket("localhost", port);
            client = new Client(socket);
            assertEquals("4.0", client.run("A 2 2"));
            client.close();
            latch.countDown();
          });
      }).start();
    }

    try { // wait for threads to finish
        latch.await();
    }
    catch (InterruptedException e) {
        e.printStackTrace();
    }

    long endTime = System.currentTimeMillis();
    System.out.println(
        "Multithreading: " + multithreading + " \nTest took: " + (endTime - startTime) + " ms");
  }
}
