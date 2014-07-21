import org.junit.Test;

import static org.junit.Assert.*;

public class RequestHandlerTest {
    @Test
    public void testClassifyRequest() throws Exception {
        assertEquals(501, RequestHandler.classify("DELETE"));

        assertEquals(200, RequestHandler.classify("GET"));
    }

    @Test
    public void testPostTwoHundred() throws Exception {
        assertEquals(200 , RequestHandler.classify("POST"));
    }
}