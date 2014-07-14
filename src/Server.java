import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private static String DEFAULT_ROOT = System.getProperty("user.dir");

    public int port;
    public String root;

    public ServerSocket server;

    public Server(ServerSocket server) throws Exception{
        this.server = server;
    }

    public Server(int port, String root, ServerSocket server) throws Exception {
        this.port = port;
        this.root = root;
        this.server = server;

        if(root == null)
            this.root = DEFAULT_ROOT;
        
        System.setProperty("user.dir", this.root);
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