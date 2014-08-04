package Responses;

import org.junit.Test;

import java.net.URLConnection;

import static org.junit.Assert.*;

public class PutResponseTest {
    @Test
    public void testPutResponse() throws Exception {
        ResponseObj resp = new PutResponse("&param=ola", "d.d");
        assertEquals(200, resp.status);
        assertEquals("OK", resp.reason);
        assertEquals("text/html", resp.headers.get("Content-Type"));
        assertEquals("&param=ola", new String(resp.body));
    }

    @Test
    public void testHasExtension() throws Exception{
        PutResponse resp = new PutResponse("&param=ola", "");
        assertEquals(true, resp.isAllowed("text-file.txt"));
        assertEquals(false, resp.isAllowed("file1"));
    }

    @Test
    public void testMethodNotAllowed() throws Exception{
        PutResponse resp = new PutResponse("&param=ola", "file1");
        assertEquals(405, resp.status);
        assertEquals("Method Not Allowed", resp.reason);
    }
}