import java.io.*;
import java.net.*;

public class Client implements ClientAndServer{
    public static Socket socket;
    public static BufferedReader br;
    public static PrintWriter writer;

    public static void main(String[] args) throws IOException {
        new Client().go();
    }

    @Override
    public void init() throws IOException {
        socket = new Socket(HOST, PORT);
        br = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Client connected");
    }

    @Override
    public void go() throws IOException {
        new Client().init();
        System.out.println("Client connected to socket");
        boolean exit = false;
        while (!socket.isOutputShutdown() && !exit){
            if (br.ready())
                exit = commandLineWrite();
        }
        socket.close();
    }

    public static boolean commandLineWrite() throws IOException {
        String[] requests = br.readLine().split(" ");
        boolean key = false;
        for (String command: COMMANDS)
            if (requests[0].equals(command)){
                key = true;
                break;
            }
        if (key && !requests[0].equals("OPTIONS"))
            writer.println(requests[0] + " " + requests[1]);
        else if (requests[0].equals("OPTIONS"))
            writer.println(requests[0]);
        else if (requests[0].equals("EXIT"))
            return true;
        else
            System.out.println("Bad command");
        return false;
    }
}