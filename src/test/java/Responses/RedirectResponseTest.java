package Responses;

import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectResponseTest {
    @Test
    public void testPostResponse() throws Exception {
        ResponseObj resp = new RedirectResponse("localhost:5000");
        assertEquals(302, resp.status);
        assertEquals("Found", resp.reason);
        assertEquals("http://localhost:5000/", resp.headers.get("Location"));
        assertEquals("This page has moved.", new String(resp.body));
    }
}