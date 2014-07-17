import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {
    @Test
    public void testClassifyRequest() throws Exception {
        String[] badRequests = {""," GET / HTTP/1.1", "GET /", "get / HTTP/1.1"};
        String notImplemented = "LET / HTTP/1.1";
        String goodRequest = "GET / HTTP/1.1";

        for(String request : badRequests){
            Request req = new Request(request);
            assertEquals(400, req.classifyRequest());
        }

        Request notImplReq = new Request(notImplemented);
        assertEquals(501, notImplReq.classifyRequest());

        Request goodReq = new Request(goodRequest);
        assertEquals(200, goodReq.classifyRequest());
    }

    @Test
    public void testPostTwoHundred() throws Exception {
        Request request = new Request("POST / HTTP/1.1");
        assertEquals(200 , request.classifyRequest());
    }

    @Test
    public void testGetters() throws Exception{
        String goodRequest = "GET / HTTP/1.1";
        Request req = new Request(goodRequest);

        if(req.classifyRequest() == 200) {
            assertEquals("GET", req.getCommand());
            assertEquals("/Users/andrew/Documents/JavaServer/", req.getPath());
            assertEquals(200, req.getStatus());
        }

        String requestWithPath = "GET /Request.java HTTP/1.1";
        Request pathReq = new Request(requestWithPath);
        assertEquals("/Users/andrew/Documents/JavaServer/Request.java", pathReq.getPath());
    }
}