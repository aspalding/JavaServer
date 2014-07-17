import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.junit.Assert.*;

public class ServerWorkerTest {

    @Test
    public void testRunValid() throws Exception {
        final String request = "GET /src/TestMedia/ HTTP/1.1\n" +
                "Host: localhost:4000\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:30.0) Gecko/20100101 Firefox/30.0\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" +
                "Accept-Language: en-US,en;q=0.5\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Connection: keep-alive\n" +
                "Cache-Control: max-age=0\n";

        Socket s = new Socket(){
            boolean closedState = false;

            @Override
            public boolean isClosed(){
                if(!closedState)
                    return false;
                else
                    return true;
            }
            @Override
            public InputStream getInputStream(){
                return new ByteArrayInputStream(request.getBytes());
            }

            public OutputStream getOutputStream(){

                return new ByteOutputStream();
            }

            @Override
            public void close(){
                closedState = true;
            }
        };

        ServerWorker worker = new ServerWorker(s);
        worker.run();
        assert s.isClosed();
    }

    @Test
    public void testRunInvalid() throws Exception {
        Socket s = new Socket(){
            boolean closedState = false;

            @Override
            public boolean isClosed(){
                if(!closedState)
                    return false;
                else
                    return true;
            }

            public InputStream getInputStream(){
                return new ByteArrayInputStream("".getBytes());
            }

            @Override
            public void close(){
                closedState = true;
            }
        };

        ServerWorker worker = new ServerWorker(s);
        worker.request = null;

        worker.run();
        assert s.isClosed();
    }

}