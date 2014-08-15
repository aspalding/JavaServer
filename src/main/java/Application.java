import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class Application{
    public static void main (String cat[]) throws Exception{
        ArgsParser.parseArguments(Arrays.asList(cat));

        System.setProperty("user.dir", ArgsParser.root);
        ServerSocket s = new ServerSocket(ArgsParser.port, 8192);

        ExecutorService exe = Executors.newFixedThreadPool(16);
        BlockingQueue<Socket> socketQueue = new LinkedBlockingDeque<>();

        while(!s.isClosed()){
            socketQueue.put(s.accept());
            ServerWorker runnable = new ServerWorker(socketQueue.take());
            exe.submit(runnable);
        }
    }
}