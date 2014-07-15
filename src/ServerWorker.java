import java.io.*;
import java.net.Socket;

public class ServerWorker implements Runnable {
    public Socket connection;

    public ServerWorker(Socket connection) throws Exception{
        this.connection = connection;
    }

    public void run() {
        try {
            while (!connection.isClosed()) {
                String request = SocketIO.readRequest(connection.getInputStream());
                System.out.println(request);
                if (request == null)
                    connection.close();
                else {
                    Request clientReq = new Request(request);
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
            e.printStackTrace();
        }
    }
}