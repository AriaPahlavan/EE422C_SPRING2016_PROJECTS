package assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class TicketClient {
    private int port;
    private ThreadedTicketClient clientThread;
    private String hostName = "";
    private String threadName = "";
    private String seatNo;
    public static ArrayList<Integer> ports = new ArrayList<>();


    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    TicketClient(String hostname, String threadname) {

        if ( Theatre.numberAvailableSeats == 0 ) {
            System.out.println("The show is sold out... Closing all ticket booths");
            System.exit(0);
        }

        Random rand = new Random();
        if ( ports.size() == 0 ){
            System.out.println("No box office made yet!");
            System.exit(0);
        }
        this.port = ports.get(rand.nextInt(ports.size()));
        this.clientThread = new ThreadedTicketClient(this, hostname, threadname, port);
        this.hostName = hostname;
        this.threadName = threadname;

    }

    TicketClient(String hostname, String name, int port) {
        this(hostname, name);
    }

    TicketClient(String name) {
        this("localhost", name);
    }

    TicketClient(String name, int port){
    	this("localhost", name);
    }

    TicketClient() {
        this("localhost", "unnamed client");
    }

    synchronized void requestTicket() {
        clientThread.run();
        sleep();
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
    private int port;


    public ThreadedTicketClient(TicketClient sc, String hostname, String threadname, int port) {
        this.sc = sc;
        this.hostname = hostname;
        this.threadname = threadname;
        this.port = port;
    }

    public void run() {
        System.out.flush();
        // Does the ticket client run method have to be in a while loop as well or not? IDK
        // I guess we need to make a queue of clients.. and randomly add clients to this queue
        // Until the show is sold out; I guess we need to terminate the program after that.
        try {
            Socket echoSocket = null;
            //synchronized(this){
            boolean connected = false;
            while(!connected){

                echoSocket = new Socket(hostname, port);
                connected = true;
            }


            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            out.println("requesting");

            String seatNo = in.readLine();

            if(seatNo != null && seatNo.equals("soldout")){
                System.out.println("Sorry " + threadname + "! The show is sold out...");
                System.exit(0);

            }
            sc.setSeatNo(seatNo);

            if ( seatNo != null ) {
                System.out.println(hostname + ", " + threadname + " got one ticket - Seat No. " + seatNo);
            } else {
                System.out.println(hostname + ", " + threadname + ", the show is sold out. Sorry.");
                System.exit(0);
            }

            echoSocket.close();
        } catch(IOException e){
            System.out.println("Sorry, system encountered an error! Please try again later.");
            System.exit(0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

