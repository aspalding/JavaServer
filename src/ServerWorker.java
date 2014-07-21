import java.io.*;
import java.net.Socket;

public class ServerWorker implements Runnable {
    public Socket connection;
    public String request;

    public ServerWorker(Socket connection) throws Exception{
        this.connection = connection;
        this.request = SocketIO.readFullRequest(connection.getInputStream());
    }

    public void run() {
        try {
            while (!connection.isClosed()) {
                //System.out.println(request);
                if (request == null)
                    connection.close();
                else {
                    RequestHandler clientReq = new RequestHandler(request.split("\n")[0]);
                    Response clientResp = new Response(new File(clientReq.getPath()));

                    SocketIO.writeResponse(
                            clientResp.responseHeader(clientReq.getStatus(), clientReq.getPath()),
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