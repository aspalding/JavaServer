package Responses;

import org.junit.Test;

import static org.junit.Assert.*;

public class PostResponseTest {
    @Test
    public void testPostResponse() throws Exception {
        ResponseObj resp = new PostResponse("&param=ola");
        assertEquals(200, resp.status);
        assertEquals("OK", resp.reason);
        assertEquals("text/html", resp.headers.get("Content-Type"));
        assertEquals("&param=ola", new String(resp.body));
    }
}