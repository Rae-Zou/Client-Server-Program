import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Represent a local host - client.
 */
public class Client {

    /**
     * a client test method send and receive quote to/from server.
     * followed an online resource in this part.
     * resource link: https://www.cnblogs.com/lichenwei/p/4069432.html
     * @throws IOException msg
     */
    public void clientDemo(String ip, int port) throws IOException {
        Socket clientSocket = new Socket(ip, port); // use the server host

        //Socket clientSocket = new Socket("localhost", 5000);  //use local host

        //get the outStream sent to server
        OutputStream out=clientSocket.getOutputStream();
        PrintWriter writer=new PrintWriter(out);
        //writer.print("Hello Server, what's the best quote today! (from client)");
        writer.flush();
        clientSocket.shutdownOutput();

        getQuoteFromSever(clientSocket);

        writer.close();
        out.close();
        clientSocket.close();
    }

    /**
     * get the quote from the server
     * @param clientSocket current socket
     * @throws IOException msg
     */
    public void getQuoteFromSever(Socket clientSocket) throws IOException {
        //get the incomingStream from the server
        InputStream in=clientSocket.getInputStream();
        InputStreamReader inputReader=new InputStreamReader(in);
        BufferedReader bufReader=new BufferedReader(inputReader);

        StringBuilder quote = new StringBuilder();
        String msg = null;

        while((msg = bufReader.readLine())!=null){
            quote.append(msg);
            System.out.println(quote);

        }

        bufReader.close();
        in.close();
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i<60; i++) {
            new Client().clientDemo(args[0], Integer.parseInt(args[1]));
        }
    }
}

