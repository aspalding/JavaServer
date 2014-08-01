package Responses;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class PartialResponseTest {
    @Test
    public void testPartialResponse() throws Exception{
        File file = new File(System.getProperty("user.dir") + "/index.html");
        PartialResponse resp = new PartialResponse(file, 4);

        assertEquals(206, resp.status);
        assertEquals("Partial Content", resp.reason);
        assertEquals("text/html", resp.headers.get("Content-Type"));
        assertEquals(4, resp.body.length);
    }

    @Test
    public void testFileToBytesNull() throws Exception {
        File fake = File.createTempFile("fake", "file");

        PartialResponse resp = new PartialResponse(fake, 12);

        String path = "C:\\";

        assertEquals(null, resp.fileToBytes(path));
    }

}