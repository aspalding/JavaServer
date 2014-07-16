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

    /*
    @Test
    public void testFullRequest() throws Exception{
        String veryLong = "GET /src/TestMedia/ HTTP/1.1\n" +
                "Host: localhost:4000\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:30.0) Gecko/20100101 Firefox/30.0\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*;q=0.8\n" +
                "Accept-Language: en-US,en;q=0.5\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Cookie: textwrapon=false; wysiwyg=textarea; __utma=111872281.1891134363.1404412714.1404412714.1404415950.2; __utmz=111872281.1404412714.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); csm-hit=b-19YGCFN3TCQTX9GFYD19|1405094768286\n" +
                "Connection: keep-alive";
            assertEquals(200 , new Request(veryLong).classifyRequest());
    }
    */

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