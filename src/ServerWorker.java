import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;

public class ServerWorker implements Runnable {
    public Request clientRequest;
    public Socket connection;
    public String request;

    public ServerWorker(Socket connection) throws Exception{
        this.connection = connection;
        this.request = SocketIO.readFullRequest(connection.getInputStream());

        try {
            this.clientRequest = new Request(request);
        } catch(NoSuchElementException e) {
            this.clientRequest = null;
        }
    }

    public void run() {
        try {
            while (!connection.isClosed()) {
                System.out.println(request);

                if(clientRequest == null)
                    connection.close();
                else {
                    Response clientResp = new Response(
                            new File(clientRequest.path)
                    );

                    //System.out.println(clientResp.responseHeader(RequestHandler.classify(clientRequest.method), clientRequest.path) + "\n" + new String(clientResp.responseBody()));

                    SocketIO.writeResponse(
                            clientResp.responseHeader(RequestHandler.classify(clientRequest.method), clientRequest.path),
                            clientResp.responseBody(),
                            connection.getOutputStream()
                    );

                    connection.close();
                }
            }
        } catch(Exception e){
            //Intentionally empty. e.printStackTrace();
        }
    }
}