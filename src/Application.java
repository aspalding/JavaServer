import java.net.ServerSocket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application{
    public static void main (String cat[]) throws Exception{
        int port;

        ArgsParser.parseArguments(Arrays.asList(cat));

        ExecutorService exe = Executors.newFixedThreadPool(4);

        if(ArgsParser.port == 0)
            port = 4000;
        else
            port = ArgsParser.port;

        ServerSocket s = new ServerSocket(port);

        exe.submit(new Server(ArgsParser.port, ArgsParser.root, s));
        exe.submit(new Server(ArgsParser.port, ArgsParser.root, s));
        exe.submit(new Server(ArgsParser.port, ArgsParser.root, s));
        exe.submit(new Server(ArgsParser.port, ArgsParser.root, s));
    }

}