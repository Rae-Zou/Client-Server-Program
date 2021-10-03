import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Represents a TCP server.
 */
public class Server {
    String ip = null;
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
                // pause the server until a connection request is made from a client
                Socket socket1 = socket.accept();
                // use threads to handle the sockets
                new MyThread(socket1,quotes).start();

                this.ip = (InetAddress.getLocalHost().getHostAddress());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getIp() {
        return ip;
    }

    public static void main(String[] args) throws IOException {
        new Server().myServer();
    }

    /**
     * MyThread to handle the TCP server sockets.
     */
    public class MyThread extends Thread {
        Socket socket;
        String[] quotes;

        /**
         * constructor.
         *
         * @param socket current socket
         * @param quotes the quotes
         */
        public MyThread(Socket socket, String[] quotes) {
            this.socket = socket;
            this.quotes = quotes;
        }

        /**
         * override the run to respond client quote.
         * followed an online resource in this part.
         * resource link: https://www.cnblogs.com/lichenwei/p/4069432.html
         */
        @Override
        public void run() {
            super.run();
            try {
                // connect with the client, get the incoming stream from the client
                InputStream in = socket.getInputStream();
                InputStreamReader inReader = new InputStreamReader(in);
                BufferedReader bufReader = new BufferedReader(inReader);

                String s = null;
                StringBuilder msg = new StringBuilder();
                while ((s = bufReader.readLine()) != null) {
                    msg.append(s);
                    String ip = (InetAddress.getLocalHost().getHostAddress());
                    System.out.println(ip + " Server says：" + msg);
                }

                sendQuoteToServer();

                bufReader.close();
                in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * get the random quote to client.
         *
         * @throws IOException msg
         */
        public void sendQuoteToServer() throws IOException {
            // send out stream quote
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            //get a random quote from the array
            Random ran = new Random();
            int num = ran.nextInt(this.quotes.length);

            String ip = (InetAddress.getLocalHost().getHostAddress());
            writer.print(ip + " says：" + this.quotes[num]);
            writer.flush();
            socket.shutdownOutput();

            writer.close();
            out.close();
        }
    }
}
