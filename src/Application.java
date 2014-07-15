import java.net.ServerSocket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application{
    public static void main (String cat[]) throws Exception{
        int port;

        ArgsParser.parseArguments(Arrays.asList(cat));

        if(ArgsParser.port == 0)
            port = 4000;
        else
            port = ArgsParser.port;

        ServerSocket s = new ServerSocket(port);

        if(ArgsParser.root != null)
            System.setProperty("user.dir", ArgsParser.root);


        ExecutorService exe = Executors.newFixedThreadPool(8);

        while(true)
            exe.submit(new ServerWorker(s.accept()));
    }
}