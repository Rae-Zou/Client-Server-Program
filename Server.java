import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Represents a TCP server.
 */
public class Server {
    String[] quotes = {
            "The Best Way To Get Started Is To Quit Talking And Begin Doing.",
            "The Pessimist Sees Difficulty In Every Opportunity.",
            "Don’t Let Yesterday Take Up Too Much Of Today.",
            "You Learn More From Failure Than From Success.",
            "It’s Not Whether You Get Knocked Down, It’s Whether You Get Up."};

    /**
     * My TCP server.
     */
    public void myServer() {
        try {
            int port = 5000;
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Sever(port 5000) is listening...");
            // listening the socket
            while (true) {
                // pause the server	until a	connection request is made from	a client
                Socket socket1 = socket.accept();
                // use threads to handle the sockets
                new MyThread(socket1,quotes).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Server().myServer();
    }

}
