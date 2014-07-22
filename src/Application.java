import java.net.ServerSocket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application{
    public static void main (String cat[]) throws Exception{
        ArgsParser.parseArguments(Arrays.asList(cat));

        System.setProperty("user.dir", ArgsParser.root);
        ServerSocket s = new ServerSocket(ArgsParser.port);

        ExecutorService exe = Executors.newFixedThreadPool(8);

        while(!s.isClosed()){
            Thread t = new Thread(new ServerWorker(s.accept()));
            exe.submit(t);
        }
    }
}