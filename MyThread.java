import java.io.*;
import java.net.Socket;
import java.util.Random;

/**
 * MyThread to handle the TCP server sockets.
 */
public class MyThread extends Thread {
    Socket socket;
    String[] quotes;

    /**
     * constructor
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
                System.out.println("Server receives：" + msg);
            }

            sendQuoteToServer();

            bufReader.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the random quote to client
     * @throws IOException msg
     */
    public void sendQuoteToServer() throws IOException {
        // send out stream quote
        OutputStream out = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(out);

        //get a random quote from the array
        Random ran = new Random();
        int num = ran.nextInt(this.quotes.length);

        writer.print(this.quotes[num]);
        writer.flush();
        socket.shutdownOutput();

        writer.close();
        out.close();
    }
}
