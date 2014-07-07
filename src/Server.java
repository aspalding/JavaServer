import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server{
    private static String DEFAULT_ROOT = System.getProperty("user.dir");
    private static int DEFAULT_PORT = 4000;

    public int port;
    public String root;

    public ServerSocket server;

    public Server() throws Exception{
        server = new ServerSocket(DEFAULT_PORT);
    }

    public Server(List<String> args) throws Exception {
        parseArguments(args);

        if(port == 0)
            port = DEFAULT_PORT;

        server = new ServerSocket(port);
    }

    public void parseArguments(List<String> args){
        int index, indexOfArgument;
        for(String arg : args){
            index = args.indexOf(arg);
            indexOfArgument = index + 1;

            if(arg.equals("-p") && indexOfArgument < args.size())
                port = parsePort(args.get(indexOfArgument));
            else if(arg.equals("-d") && indexOfArgument < args.size())
                root = parseRoot(args.get(indexOfArgument));
        }
    }

    public String parseRoot(String input){
        if(Files.isDirectory(Paths.get(input)))
            return input;
        else {
            System.out.println("Not valid, using default");
            return DEFAULT_ROOT;
        }
    }

    public int parsePort(String input){
        int result = 0;
        try {
            result = Integer.parseInt(input);
        } catch (NumberFormatException e){
            System.out.println("Not valid, using default");
        }

        if(isValidPort(result))
            return result;
        else
            return DEFAULT_PORT;
    }

    public boolean isValidPort(int input){
        return input > 0 && input < 65535;
    }
    
    public String process(Socket socket) throws Exception{
        BufferedReader requestReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return requestReader.readLine();
    }

    public void run() throws Exception{
        while(true){
            Socket s;
            s = server.accept();
            String request = process(s);

            System.out.println(request);

            if(request == null)
                s.close();
            else{
                Request clientReq = new Request(request);
                Response clientResp = new Response(new FileProd(clientReq.getPath()));
                clientResp.write(
                    clientResp.responseHeader(clientReq.getStatus(), clientReq.getPath()),
                    clientResp.responseBody(),
                    s
                );
                s.close();
            }
        }
    }

    public static void main (String cat[]) throws Exception{
        //List<String> args = Arrays.asList(cat);
        //Server simpleServer = new Server();
        Server simpleServer = new Server(Arrays.asList(cat));
        System.out.println("Server started at localhost:" + simpleServer.port);
        simpleServer.run();
    }
}