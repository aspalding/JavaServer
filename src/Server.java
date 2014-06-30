import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    private static int PORT = 4000;

    private ServerSocket server;

    public Server() throws Exception{
        server = new ServerSocket(PORT);
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
            Request clientReq = new Request(request);
            Response clientResp = new Response(new FileProd(clientReq.getPath()));
            clientResp.write(clientResp.responseBody(clientReq.getPath()),
                    clientResp.responseString(clientReq.getStatus()), s);
            s.close();
        }
    }

    public static void main (String cat[]) throws Exception{
        Server simpleServer = new Server();
        System.out.println("Server started at localhost:4000");
        simpleServer.run();
    }
}