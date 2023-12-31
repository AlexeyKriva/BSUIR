import java.io.*;
import java.net.*;
import java.util.*;

public class Server implements ClientAndServer{
    public static ServerSocket serverSocket;
    public static Socket clientSocket;
    public static InputStream in;
    BufferedReader br;
    public static final HashMap<String, String> OPTIONS = new HashMap<>(){
        {put("GET", "File discovery and output");
        put("POST", "Create a new file in the specified directory");
        put("OPTIONS", "List of valid commands");
        put("EXIT", "Exiting the programme");}
    };

    public static void main(String[] args) throws IOException {
        new Server().go();
    }

    @Override
    public void init() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Server is running");
    }

    @Override
    public void go() throws IOException{
        new Server().init();
        clientSocket = serverSocket.accept();
        in = clientSocket.getInputStream();
        br = new BufferedReader(new InputStreamReader(in));
        String[] s = br.readLine().split(" ");
        while (true) {
            if (s[0].equals("EXIT")) {
                serverSocket.close();
                clientSocket.close();
                break;
            } else {
                if (s[0].equals("GET")){
                    get(s[1]);
                } else if (s[0].equals("POST")){
                    post(s[1]);
                } else if (s[0].equals("OPTIONS")){
                    options();
                }
            }
            s = br.readLine().split(" ");
        }
    }

    public void get(String path) throws IOException {
        File file = new File(path);
        if (file.exists()){
            System.out.println("File is found\nContents of the file:\n");
            BufferedReader fileReader = new BufferedReader(new FileReader(path));
            String line = "";
            boolean key = true;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
                key = false;
            }
            if (key)
                System.out.println("File is empty");
        } else {
            System.out.println("File isn't found");
        }
    }

    public void post(String path) throws IOException {
        File file = new File(path);
        if (file.createNewFile())
            System.out.println("File was created");
        else
            System.out.println("File wasn't created");
    }

    public void options() {
        System.out.println("Valid commands:");
        for (Map.Entry<String, String> entry: OPTIONS.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}