package httpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

import com.sun.net.httpserver.*;

public class Server implements Runnable {

    private String threadName;
    private Scanner n;
    private Thread thread;
    private final int port;
    private HttpServer server;

    Server(final String threadName, final int port){
        this.threadName = threadName;
        this.port = port;
    }

    private void openServer(final int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress("localhost", port), 0);
        System.out.println("Server is running on port " + port + " ... ");
        server.createContext("/", new myHandler(port));
        server.start();
    }

    @Override
    public void run() {
        try {
            openServer(this.port);
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

    public void start(){
        System.out.println("Starting " + threadName + " ...");
        if (thread == null) {
            thread = new Thread (this, threadName);
            thread.start();
         } else {
             System.out.println("thread has already been created");
         }
    }

    public void stop(){
        server.stop(1);
        System.out.println(threadName + " has stopped...");
    }

    public String getThreadname(){
        return this.threadName;
    }
}