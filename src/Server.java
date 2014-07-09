import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private static String DEFAULT_ROOT = System.getProperty("user.dir");
    private static int DEFAULT_PORT = 4000;

    public int port;
    public String root;

    public ServerSocket server;

    public Server() throws Exception{
        server = new ServerSocket(DEFAULT_PORT);
    }

    public Server(int port, String root) throws Exception {
        this.port = port;
        this.root = root;

        if(port == 0)
            this.port = DEFAULT_PORT;

        if(root == null)
            this.root = DEFAULT_ROOT;

        server = new ServerSocket(this.port);
    }

    public String readRequest(InputStream input){
        try {
            BufferedReader requestReader = new BufferedReader(new InputStreamReader(input));
            String request = requestReader.readLine();
            return request;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void writeResponse(String responseHeader, byte[] responseBody, OutputStream output) throws Exception{
        try {
            BufferedOutputStream out = new BufferedOutputStream(output);
            out.write(responseHeader.getBytes());
            out.write(responseBody);
            out.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                Socket s;
                s = server.accept();
                String request = readRequest(s.getInputStream());

                System.out.println(request);

                if (request == null)
                    s.close();
                else {
                    Request clientReq = new Request(request);
                    Response clientResp = new Response(new File(clientReq.getPath()));

                    writeResponse(
                            clientResp.responseHeader(clientReq.getStatus(), clientReq.getPath()),
                            clientResp.responseBody(),
                            s.getOutputStream()
                    );

                    s.close();
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}