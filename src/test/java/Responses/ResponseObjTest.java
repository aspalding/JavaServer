package Responses;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ResponseObjTest {
    ResponseObj obj;

    @Before
    public void setUp() throws Exception{
        obj = new ResponseObj();
        int status = 200;
        String reason = "OK";
        String body = "hello world";
        HashMap<String, String> hm = new HashMap<String, String>(){
            {
                put("Content-Type", "text/html");
                put("Content-Length", "1");
            }
        };

        obj.status = status;
        obj.reason = reason;
        obj.body = body.getBytes();
        obj.headers = hm;
    }

    @Test
    public void testToString() throws Exception{
        String itShould = "HTTP/1.1 200 OK\nContent-Length: 1\nContent-Type: text/html\n\nhello world";

        assertEquals(itShould, obj.toString());
    }

    @Test
    public void testStatusAndHeadersToString() throws Exception{
        String itShould = "HTTP/1.1 200 OK\nContent-Length: 1\nContent-Type: text/html\n";

        assertEquals(itShould, obj.statusAndHeadersToString());
    }
}