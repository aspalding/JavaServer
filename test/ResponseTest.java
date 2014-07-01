import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest {

    @Test
    public void testValidPathResponseString() throws Exception {
        FileFake fk = new FileFake(true);
        Response resp = new Response(fk);
        assert resp.responseHeader(200, "").equals("HTTP/1.1 200 OK\nContent-Type: null\n\n");
    }

    @Test
    public void testInvalidPathResponseString() throws Exception {
        FileFake fk = new FileFake(false);
        Response resp = new Response(fk);
        assert resp.responseHeader(200, "").equals("HTTP/1.1 404 Not Found\n\n");
    }

    @Test
    public void testResponseBody() throws Exception {
        FileFake fk = new FileFake(true, "hello world");
        Response resp = new Response(fk);
        assert resp.responseHeader(200, "").equals("HTTP/1.1 200 OK\nContent-Type: null\n\n");
        assert new String(resp.responseBody()).equals("hello world");
    }


}