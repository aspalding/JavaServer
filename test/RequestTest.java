import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {
    @Test
    public void testIsBadRequest() throws Exception {
        String[] badRequests = {""," GET ./ HTTP/1.1", "GET ./", "get ./ HTTP/1.1"};
        String notImplemented = "LET ./ HTTP/1.1";
        String goodRequest = "GET ./ HTTP/1.1";

        for(String request : badRequests){
            Request req = new Request(request);
            assert req.classifyRequest() == 400;
        }

        Request notImplReq = new Request(notImplemented);
        assert notImplReq.classifyRequest() == 501;

        Request goodReq = new Request(goodRequest);
        assert goodReq.classifyRequest() == 200;
    }

    @Test
    public void testGetters() throws Exception{
        String goodRequest = "GET HTTP/1.1";
        Request req = new Request(goodRequest);

        if(req.classifyRequest() == 200) {
            assert req.getCommand().equals("GET");
            assert req.getPath().equals("index.html");
        }

        String requestWithPath = "GET /Request.java HTTP/1.1";
        Request pathReq = new Request(requestWithPath);
        assert pathReq.getPath().equals("Request.java");
    }
}