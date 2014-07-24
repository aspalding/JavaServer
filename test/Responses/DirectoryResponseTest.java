package Responses;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class DirectoryResponseTest {
    ResponseObj resp;

    @Before
    public void setUp() throws Exception{
        File fake = new File(""){
            @Override
            public boolean isDirectory(){
                return true;
            }
        };
        resp = new DirectoryResponse(fake);
    }

    @Test
    public void testBodyDirectoryResponse() throws Exception {
        assertEquals("<ul>", new String(resp.body).substring(0, 4));
        assertEquals(200, resp.status);
        assertEquals("OK", resp.reason);
        assertEquals("text/html", resp.headers.get("Content-Type"));
    }
}