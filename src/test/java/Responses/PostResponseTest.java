package Responses;

import org.junit.Test;

import static org.junit.Assert.*;

public class PostResponseTest {
    @Test
    public void testPostResponse() throws Exception {
        ResponseObj resp = new PostResponse("&param=ola", "file1");
        assertEquals(200, resp.status);
        assertEquals("OK", resp.reason);
        assertEquals("text/html", resp.headers.get("Content-Type"));
        assertEquals("&param=ola", new String(resp.body));
    }

    @Test
    public void testHasExtension() throws Exception{
        PostResponse resp = new PostResponse("&param=ola", "dajsf");
        assertEquals(true, resp.isAllowed("file1"));
        assertEquals(false, resp.isAllowed("text-file.txt"));
    }

    @Test
    public void testMethodNotAllowed() throws Exception{
        PostResponse resp = new PostResponse("&param=ola", "text-file.txt");
        assertEquals(405, resp.status);
        assertEquals("Method Not Allowed", resp.reason);
    }

}