import java.io.*;

public class SocketIO{
    public static String readRequest(InputStream input) throws Exception {
        BufferedReader requestReader = new BufferedReader(new InputStreamReader(input));
        return requestReader.readLine();
    }

    public static String readFullRequest(InputStream input) throws Exception {
        byte[] request = new byte[8192];
        input.read(request);
        return new String(request);
    }

    public static void writeResponse(String response, OutputStream output) throws Exception {
        BufferedOutputStream out = new BufferedOutputStream(output);
        out.write(response.getBytes());
        out.close();
    }

    public static void writeResponse(String responseHeader, byte[] responseBody, OutputStream output) throws Exception {
        BufferedOutputStream out = new BufferedOutputStream(output);
        out.write(responseHeader.getBytes());
        out.write("\n".getBytes());
        out.write(responseBody);
        out.close();
    }

}