import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application{
    public static void main (String cat[]) throws Exception{
        ArgsParser.parseArguments(Arrays.asList(cat));

        ExecutorService exe = Executors.newSingleThreadExecutor();

        exe.submit(new Server(ArgsParser.port, ArgsParser.root));


        //Server simpleServer = new Server(Arrays.asList(cat));
        //System.out.println("Server started at localhost:" + simpleServer.port);
        //simpleServer.run();
    }

}