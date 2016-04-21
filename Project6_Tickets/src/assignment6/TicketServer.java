package assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class TicketServer {
    protected int PORT = 2222;
    String name;
    // EE422C: no matter how many concurrent requests you get,
    // do not have more than three servers running concurrently
    final static int MAXPARALLELTHREADS = 3;


    private static int numTicketServers = 0;

    // TicketServer should function to hold the layout of the theater to sell tickets to
    final static Theatre theater = new Theatre();

    public TicketServer(int portNumber, String officeName) throws IOException {
        numTicketServers++;
        if(numTicketServers > MAXPARALLELTHREADS){
            System.err.println("Error - max server threads exceeded");
            return;
        }
        TicketClient.ports.add(portNumber);
        PORT = portNumber;
        name = officeName;

        ThreadedTicketServer serverThread = new ThreadedTicketServer(PORT, this);
        //Thread thread = new Thread(serverThread);


        serverThread.setName(name);
        serverThread.start();

    }


    // Gets the best available seat from the theater
    public Seat bestAvailableSeat() {
        return theater.bestAvailableSeat();
    }

    // Marks the seat passed to this method as taken/sold in the theater
    public synchronized boolean markAvailableSeat(Seat markedSeat) {
        return theater.markAvailableSeatTaken(markedSeat);
    }
}

class ThreadedTicketServer extends Thread{

    private String hostname = "127.0.0.1";
    private String threadname = "";
    private String testcase;
    private TicketServer server;
    private int port;

    public ThreadedTicketServer(int portNumber, TicketServer t){
        server = t;
        port = portNumber;
    }

    @Override
    public void start(){
        super.start();
    }

    public void run() {
        // TODO 422C
        ServerSocket serverSocket = null;
        System.out.flush();
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(5000);

            // Basic server running loop from Lisa's lab slides, modified a bit
            boolean running = true;

            while ( running ) {

                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                boolean successfullySold = false;
                Seat bestSeat = null;

                String line = in.readLine();
                while(line == null || !line.equals("requesting")){}
                //if(line != null && line.equals("requesting")){
                while ( !successfullySold ) {
                    // Gets the best seat currently from the theater
                    bestSeat = server.bestAvailableSeat();

                    // If no seat was available to be found, close the socket and get ready to finish execution
                    if ( bestSeat == null ) {
                        running = false;
                        clientSocket.close();
                        out.println("soldout");

                        //break inner while loop
                        break;
                    }


                    // Mark the seat as available
                    // If not successful (meaning already sold to someone),
                    // Try finding the next best seat.
                    if ( server.markAvailableSeat(bestSeat) ) {
                        successfullySold = true;
                    }
                    else {
                        System.out.println("Sorry, " + threadname + " failed to reserve seat no. " + bestSeat.getSeatNo());
                    }
                }
                //}


                //output the Seat No., unless there's no seat left
                if ( bestSeat != null ) out.println(bestSeat.getSeatNo() + " from " + this.getName());
                else out.println("soldout");


                //Closes socket and streams associated with this particular socket
                clientSocket.close();
                out.close();
                in.close();
            }

            serverSocket.close();

        } catch (SocketTimeoutException ste) {
            System.err.println("No more clients - Closing " + this.getName());
            if ( ThreadedTicketServer.activeCount() == 0)
                System.exit(0);
        } catch (IOException e) {
            System.err.println("Error - Invalid Client Socket Used");

            e.printStackTrace();
        } finally {
            if(serverSocket != null){
                try{
                    serverSocket.close();
                }
                catch(Exception e){
                    System.err.println("Error - Closing Server Socket");
                }
            }
        }
    }
}