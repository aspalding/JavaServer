import java.io.*;

public class SocketIO{
    public static String readRequest(InputStream input) throws Exception {
        BufferedReader requestReader = new BufferedReader(new InputStreamReader(input));
        /*
        String request = "";
        int next = 0;


        do {
            request += (char)next;
            next = requestReader.read();
        } while (next != -1);

        return request.substring(1, request.length());
        */
        return requestReader.readLine();
    }

    public static void writeResponse(String responseHeader, byte[] responseBody, OutputStream output) throws Exception {
        BufferedOutputStream out = new BufferedOutputStream(output);
        out.write(responseHeader.getBytes());
        out.write(responseBody);
        out.close();
    }
}