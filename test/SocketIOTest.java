    import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class SocketIOTest {
    @Test
    public void testReadRequest() throws Exception {
        InputStream input = new ByteArrayInputStream("hello world".getBytes());

        assertEquals("hello world", SocketIO.readRequest(input));
    }

    /*
    @Test
    public void testReadRequestWithHeadersAndBody() throws Exception {
        String text = "This is not a well formed request.\n" +
                      "But it has multiple lines.\n\n" +
                      "With a body like this.";

        InputStream input = new ByteArrayInputStream(text.getBytes());
        assertEquals(text, SocketIO.readRequest(input));
    }
    */

    @Test
    public void testWriteResponse() throws Exception {
        OutputStream output = new ByteArrayOutputStream();
        output.write("hello".getBytes());
        output.write("world".getBytes());

        OutputStream written = new ByteArrayOutputStream();
        SocketIO.writeResponse("hello", "world".getBytes(), written);

        assertEquals(output.toString(), written.toString());
    }
}