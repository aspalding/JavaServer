import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server{
    private static int DEFAULT_PORT = 4000;

    public int port;
    public String root;

    private ServerSocket server;

    public Server() throws Exception{
        server = new ServerSocket(DEFAULT_PORT);
    }

    public ServerSocket getServerSocket(){
        return server;
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
            System.out.println(s.toString());

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

    public void parseArguments(List<String> args){
        for(String arg : args){
            if(arg == "-p" && (args.size() == 2 || args.size() == 4)){
                int indexOfModifier = args.indexOf(arg);
                try {
                    port = Integer.parseInt(args.get(indexOfModifier + 1));
                } catch (NumberFormatException e){
                    System.out.println("Not valid, using default");
                    port = DEFAULT_PORT;
                }
            }
            else if(arg == "-d" && (args.size() == 2 || args.size() == 4)){
                int indexOfModifier = args.indexOf(arg);
                try {
                    root = args.get(indexOfModifier + 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }
        }
    }

    public static void main (String cat[]) throws Exception{
        List<String> args = Arrays.asList(cat);

        Server simpleServer = new Server();
        System.out.println("Server started at localhost:4000");
        simpleServer.run();
    }
}