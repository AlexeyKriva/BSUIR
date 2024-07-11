import java.io.IOException;

public interface ClientAndServer {
    public static final String HOST = "localhost";
    public static final int PORT = 8000;
    public static final String[] COMMANDS = {"GET","POST","OPTIONS"};
    void init() throws IOException;
    void go() throws IOException;
}
