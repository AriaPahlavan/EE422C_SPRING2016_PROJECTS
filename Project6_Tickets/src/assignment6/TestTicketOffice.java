package assignment6;

import org.junit.Test;

import java.util.ArrayList;

public class TestTicketOffice {

    public static int score = 0;

    public static void main(String[] args) {
        try {
            TicketServer thread1 = new TicketServer(16790, "Box Office A");
            TicketServer thread2 = new TicketServer(16791, "Box Office B");
            TicketServer thread3 = new TicketServer(16792, "Box Office C");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        basicServerTest();
        testServerCachedHardInstance();
        twoNonConcurrentServerTest();
    	twoConcurrentServerTest();
        multipleServerTestOne();
        multipleServerTestTwo();
        clientsTest1000();
        fillTheTheaterTestHardcodedPorts();
        fillTheTheaterTest();
        fillTheTheaterTestRandomPorts();
    }

    @Test
    public static void basicServerTest() {
        TicketClient client = new TicketClient("client1");
        client.requestTicket();
    }

    @Test
    public static void testServerCachedHardInstance() {
        TicketClient client1 = new TicketClient("localhost", "c1");
        TicketClient client2 = new TicketClient("localhost", "c2");
        client1.requestTicket();
        client2.requestTicket();

    }

    @Test
    public static void twoNonConcurrentServerTest() {
        TicketClient c1 = new TicketClient("nonconc1");
        TicketClient c2 = new TicketClient("nonconc2");
        TicketClient c3 = new TicketClient("nonconc3");
        c1.requestTicket();
        c2.requestTicket();
        c3.requestTicket();
    }

    @Test
    public static void multipleServerTestTwo() {
        final TicketClient c1 = new TicketClient("conc1");
        final TicketClient c2 = new TicketClient("conc2");
        final TicketClient c3 = new TicketClient("conc3");
        Thread t1 = new Thread() {
            public void run() {
                c1.requestTicket();
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                c2.requestTicket();
            }
        };
        Thread t3 = new Thread() {
            public void run() {
                c3.requestTicket();
            }
        };
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public static void twoConcurrentServerTest() {

        final TicketClient c1 = new TicketClient("conc1");
        final TicketClient c2 = new TicketClient("conc2");
        final TicketClient c3 = new TicketClient("conc3");
        Thread t1 = new Thread() {
            public void run() {
                c1.requestTicket();
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                c2.requestTicket();
            }
        };
        Thread t3 = new Thread() {
            public void run() {
                c3.requestTicket();
            }
        };
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public static void multipleServerTestOne(){

        final TicketClient c1 = new TicketClient("conc1");
        final TicketClient c2 = new TicketClient("conc2");
        Thread t1 = new Thread() {
            public void run() {
                c1.requestTicket();
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                c2.requestTicket();
            }
        };
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public static void clientsTest1000(){

        int num = 1000;
        for(int i = 0; i < num; i++){
            final TicketClient c1 = new TicketClient("conc" + i);

            Thread t1 = new Thread() {
                public void run() {
                    c1.requestTicket();
                }
            };

            t1.start();
        }
    }

    @Test
    public static void fillTheTheaterTestHardcodedPorts(){

        ArrayList<TicketClient> line1 = new ArrayList<TicketClient>();
        ArrayList<TicketClient> line2 = new ArrayList<TicketClient>();

        for(int i = 0; i < 1000; i++){
            line1.add(new TicketClient("client" + (i+1), 554));
        }

        for(int i = 1000; i < 2000; i++){
            line2.add(new TicketClient("client" + (i+1), 5486));
        }

        Thread t1 = new Thread() {
            public void run() {
                while(!line1.isEmpty()){
                    line1.remove(0).requestTicket();
                }

            }
        };
        Thread t2 = new Thread() {
            public void run() {
                while(!line2.isEmpty()){
                    line2.remove(0).requestTicket();
                }
            }
        };
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public static void fillTheTheaterTest(){

        ArrayList<TicketClient> line1 = new ArrayList<TicketClient>();

        for(int i = 0; i < 1000; i++){
            line1.add(new TicketClient("client" + (i+1)));
        }

        Thread t1 = new Thread() {
            public void run() {
                while(!line1.isEmpty()){
                    line1.remove(0).requestTicket();
                }

            }
        };
        t1.start();
        try {
            t1.join();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public static void fillTheTheaterTestRandomPorts(){

        ArrayList<TicketClient> line1 = new ArrayList<TicketClient>();

        for(int i = 0; i < 1000; i++){
            line1.add(new TicketClient("client" + (i+1)));
        }

        while(!line1.isEmpty()){
            line1.remove(0).requestTicket();
        }

    }
}