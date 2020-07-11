package httpServer;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.*;

public class myHandler implements HttpHandler {

    private int port;

    myHandler(int port) {
        this.port = port;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "<h1>Server start success if you see this message</h1>" + "<h1>Port: " + port + "</h1>";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}