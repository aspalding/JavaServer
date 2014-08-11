import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class Application{
    public static void main (String cat[]) throws Exception{
        ArgsParser.parseArguments(Arrays.asList(cat));

        System.setProperty("user.dir", ArgsParser.root);
        ServerSocket s = new ServerSocket(ArgsParser.port, 4096);

        ExecutorService exe = Executors.newFixedThreadPool(32);
        BlockingQueue<Socket> socketQueue = new LinkedBlockingDeque<>();

        while(!s.isClosed()){
            socketQueue.put(s.accept());
            exe.submit(new ServerWorker(socketQueue.take()));
        }
    }
}