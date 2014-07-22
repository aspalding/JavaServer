import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerWorker implements Runnable {
    private static final Logger reqRespLog = Logger.getLogger( ServerWorker.class.getName() );
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
                if(clientRequest == null)
                    connection.close();
                else {
                    Response clientResp = new Response(
                            new File(clientRequest.path)
                    );

                    SocketIO.writeResponse(
                            clientResp.responseHeader(RequestHandler.classify(clientRequest.method), clientRequest.path),
                            clientResp.responseBody(),
                            connection.getOutputStream()
                    );

                    reqRespLog.log(
                            Level.FINE,
                            request +
                            clientResp.responseHeader(RequestHandler.classify(clientRequest.method), clientRequest.path) +
                            "\n" + new String(clientResp.responseBody())
                    );

                    connection.close();
                }
            }
        } catch(Exception e){
            //Intentionally empty. e.printStackTrace();
        }
    }
}