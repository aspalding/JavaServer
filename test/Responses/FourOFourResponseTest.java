package Responses;

import org.junit.Test;

import static org.junit.Assert.*;

public class FourOFourResponseTest {

    @Test
    public void testFourOFourResponse() throws Exception {
        ResponseObj resp = new FourOFourResponse();
        assertEquals(404, resp.status);
        assertEquals("Not Found", resp.reason);
        assertEquals("text/html", resp.headers.get("Content-Type"));
        assertEquals("Page Not Found.", new String(resp.body));
    }

}