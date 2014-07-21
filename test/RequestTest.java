import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.*;

public class RequestTest {
    Request request;

    @Before
    public void setUp() throws Exception {
        String req = "GET /src/TestMedia/ HTTP/1.1\n" +
                "Host: localhost:4000\n" +
                "Connection: keep-alive\n" +
                "Cache-Control: max-age=0\n\n" +
                "body=notnil";

        request = new Request(req);
    }


    @Test
    public void testRequestMethod() throws Exception {
        String itShould = "GET";
        assertEquals(itShould, request.method);
    }

    @Test
    public void testRequestPath() throws Exception {
        String itShould = "/src/TestMedia/";
        assert request.path.contains(itShould);
    }

    @Test
    public void testHeaders() throws Exception{
        Hashtable<String, String> ht = new Hashtable<String, String>(){
            {
                put("Host", "localhost:4000");
                put("Connection", "keep-alive");
                put("Cache-Control", "max-age=0");
            }
        };

        assertEquals(ht, request.headers);
    }

    @Test
    public void testRequestBody() throws Exception {
        String itShould = "body=notnil";
        assertEquals(itShould, request.body);
    }


    @Test
    public void testRequestwoBody() throws Exception {
        String reqNoBody = "GET /src/TestMedia/ HTTP/1.1\n" +
                "Host: localhost:4000\n" +
                "Connection: keep-alive\n" +
                "Cache-Control: max-age=0\n";

        Request requestNoBody = new Request(reqNoBody);

        String itShould = "GET";
        assertEquals(itShould, requestNoBody.method);

        String itReallyShould = "/src/TestMedia/";
        assert requestNoBody.path.contains(itReallyShould);

        Hashtable<String, String> ht = new Hashtable<String, String>(){
            {
                put("Host", "localhost:4000");
                put("Connection", "keep-alive");
                put("Cache-Control", "max-age=0");
            }
        };

        assertEquals(ht, requestNoBody.headers);
    }

}