package Responses;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetParameterResponseTest {
    GetParameterResponse resp;

    @Before
    public void setUp() throws Exception {
        String pathParameter = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!" +
                "%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%" +
                "5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        resp = new GetParameterResponse(pathParameter);

    }

    @Test
    public void testStatus() throws Exception {
        assertEquals(200, resp.status);
    }

    @Test
    public void testReason() throws Exception {
        assertEquals("OK", resp.reason);
    }

    @Test
    public void testHeaders() throws Exception {
        assertEquals("text/html", resp.headers.get("Content-Type"));
    }

    @Test
    public void testDecodeParameters() throws Exception {
        String terribleParameter = "Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20" +
                "%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20" +
                "%5D%3A%20%22is%20that%20all%22%3F";
        String itShould = "Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?";
        assertEquals(itShould, resp.decodeParameter(terribleParameter));
    }

    /*
    @Test
    public void testBody() throws Exception{
        assertEquals("This page has moved.", new String(resp.body));
    }
    */
}