package Responses;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileResponseTest {
    @Test
    public void testGenerateBody() throws Exception {
        File fake = File.createTempFile("fake", "file");

        FileResponse resp = new FileResponse(fake);

        assertEquals("", new String(resp.body));
    }

    @Test
    public void testGenerateStatusAndReason() throws Exception {
        File fake = File.createTempFile("fake", "file");
        FileResponse resp = new FileResponse(fake);

        assertEquals(200, resp.status);
        assertEquals("OK", resp.reason);
    }

    @Test
    public void testGenerateContentTypeHeader() throws Exception {
        File fake = File.createTempFile("fake", "file");
        FileResponse resp = new FileResponse(fake);

        assertEquals(null, resp.headers.get("Content-Type"));
    }

    @Test
    public void testFileToBytes() throws Exception {
        File fake = File.createTempFile("fake", "file");

        FileResponse resp = new FileResponse(fake);

        String path = fake.getAbsolutePath();

        assertEquals("", new String(resp.fileToBytes(path)));
    }

    @Test
    public void testFileToBytesNull() throws Exception {
        File fake = File.createTempFile("fake", "file");

        FileResponse resp = new FileResponse(fake);

        String path = "C:\\";

        assertEquals(null, resp.fileToBytes(path));
    }

}