package Responses;

import org.junit.Test;

import static org.junit.Assert.*;

public class OptionsResponseTest {
    @Test
    public void testOptionsResponse() throws Exception {
        ResponseObj resp = new OptionsResponse();
        assertEquals(200, resp.status);
        assertEquals("OK", resp.reason);
        assertEquals("text/html", resp.headers.get("Content-Type"));
        assertEquals("GET,HEAD,POST,OPTIONS,PUT", resp.headers.get("Allow"));
        assertEquals("", new String(resp.body));
    }

}