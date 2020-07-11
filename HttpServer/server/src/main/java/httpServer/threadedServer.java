package httpServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class threadedServer {

    private static Scanner n = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("How many servers: ");
        int numOfServers = n.nextInt();

        validationOfNumOfServers(numOfServers);

        List<Server> servers = new ArrayList<>(numOfServers);
        for (int i = 0; i < numOfServers; i++) {
            servers.add(new Server("server" + i, 9000 + i));
            servers.get(i).start();
            String uri = "localhost:" + (9000+i);
            openChromeWindows(uri);
        }

        String stopCommand = "";
        System.out.println("Enter \"stop\" to stop the servers");
        stopCommand = n.next();
        System.out.println(servers.size());
        while (true) {
            if (stopCommand.equals("stop")) {
                for (Server server : servers) {
                    server.stop();
                }
                n.close();
                break;
            }
        }
    }

    private static void validationOfNumOfServers(int numOfServers) {
        if (numOfServers > 10) {
            throw new IndexOutOfBoundsException("You cannot create more than 10 servers");
        }
    }

    private static void openChromeWindows(String uri) {
        try {
            Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start chrome http://" + uri });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}