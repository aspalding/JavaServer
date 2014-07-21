import org.junit.Test;

import static org.junit.Assert.*;

public class RequestHandlerTest {
    @Test
    public void testClassifyRequest() throws Exception {
        String[] badRequests = {""," GET / HTTP/1.1", "GET /", "get / HTTP/1.1"};
        String notImplemented = "LET / HTTP/1.1";
        String goodRequest = "GET / HTTP/1.1";

        for(String request : badRequests){
            RequestHandler req = new RequestHandler(request);
            assertEquals(400, req.classifyRequest());
        }

        RequestHandler notImplReq = new RequestHandler(notImplemented);
        assertEquals(501, notImplReq.classifyRequest());

        RequestHandler goodReq = new RequestHandler(goodRequest);
        assertEquals(200, goodReq.classifyRequest());
    }

    @Test
    public void testPostTwoHundred() throws Exception {
        RequestHandler request = new RequestHandler("POST / HTTP/1.1");
        assertEquals(200 , request.classifyRequest());
    }

    @Test
    public void testGetters() throws Exception{
        String goodRequest = "GET / HTTP/1.1";
        RequestHandler req = new RequestHandler(goodRequest);

        if(req.classifyRequest() == 200) {
            assertEquals("GET", req.getCommand());
            assertEquals("/Users/andrew/Documents/JavaServer/", req.getPath());
            assertEquals(200, req.getStatus());
        }

        String requestWithPath = "GET /Request.java HTTP/1.1";
        RequestHandler pathReq = new RequestHandler(requestWithPath);
        assertEquals("/Users/andrew/Documents/JavaServer/Request.java", pathReq.getPath());
    }
}