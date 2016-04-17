package assignment6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TicketClient {
    public final String LOCAL_HOST = "127.0.0.1";
    private ThreadedTicketClient clientThread;
    private String result = "dummy";
    private String hostName = "";
    private String threadName = "";

    TicketClient(String hostname, String threadname) {
        clientThread = new ThreadedTicketClient(this, hostname, threadname);
        hostName = hostname;
        threadName = threadname;
    }

    TicketClient(String name) {
        this("localhost", name);
    }

    TicketClient() {
        this("localhost", "unnamed client");
    }

    void requestTicket() {
        // TODO thread.run()
        clientThread.run();
        System.out.println(hostName + "," + threadName + " got one ticket");
    }

    void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


class ThreadedTicketClient implements Runnable {
    private String hostname = "127.0.0.1";
    private String threadname = "X";
    private TicketClient sc;


    public ThreadedTicketClient(TicketClient sc, String hostname, String threadname) {
        this.sc = sc;
        this.hostname = hostname;
        this.threadname = threadname;
    }

    public void run() {
        System.out.flush();
        try {
            Socket echoSocket = new Socket(hostname, TicketServer.PORT);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            echoSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


