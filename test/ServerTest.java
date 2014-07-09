import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ServerTest {
    Server server;

    @Before
    public void setUp() throws Exception{
        server = new Server();
    }

    @After
    public void tearDown() throws Exception{
        server.server.close();
        server = null;
    }

    @Test
    public void testReadRequest() throws Exception {
        InputStream input = new ByteArrayInputStream("hello world".getBytes());

        assertEquals("hello world", server.readRequest(input));
    }

    @Test
    public void testWriteResponse() throws Exception {
        OutputStream output = new ByteArrayOutputStream();
        output.write("hello".getBytes());
        output.write("world".getBytes());

        OutputStream written = new ByteArrayOutputStream();
        server.writeResponse("hello", "world".getBytes(), written);

        assertEquals(output.toString(), written.toString());
    }
}