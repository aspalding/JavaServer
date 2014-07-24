package Responses;

import Requests.Request;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ResponseRouterTest {

    @Test
    public void testRoute() throws Exception {
        Request indexReq = new Request(
                "GET / HTTP/1.1\n" +
                "Host: localhost:4000\n" +
                "Connection: keep-alive\n" +
                "Cache-Control: max-age=0\n\n" +
                "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(indexReq) instanceof FileResponse));

        Request fileReq = new Request(
                "GET /src/Application.java HTTP/1.1\n" +
                        "Host: localhost:4000\n" +
                        "Connection: keep-alive\n" +
                        "Cache-Control: max-age=0\n\n" +
                        "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(fileReq) instanceof FileResponse));

        Request postReq = new Request(
                "POST /form HTTP/1.1\n" +
                        "Host: localhost:4000\n" +
                        "Connection: keep-alive\n" +
                        "Cache-Control: max-age=0\n\n" +
                        "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(postReq) instanceof PostResponse));

        Request optReq = new Request(
                "OPTIONS /method_options HTTP/1.1\n" +
                        "Host: localhost:4000\n" +
                        "Connection: keep-alive\n" +
                        "Cache-Control: max-age=0\n\n" +
                        "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(optReq) instanceof OptionsResponse));

        Request invalid = new Request(
                "GET C: HTTP/1.1\n" +
                        "Host: localhost:4000\n" +
                        "Connection: keep-alive\n" +
                        "Cache-Control: max-age=0\n\n" +
                        "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(invalid) instanceof FourOFourResponse));

        Request partial = new Request(
                "GET /src/Application.java HTTP/1.1\n" +
                        "Host: localhost:4000\n" +
                        "Connection: keep-alive\n" +
                        "Range: bytes=0-4\n" +
                        "Cache-Control: max-age=0\n\n" +
                        "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(partial) instanceof PartialResponse));
    }

    @Test
    public void testDirectoryHasIndex() throws Exception{
        File real = new File(System.getProperty("user.dir"));
        assertEquals(true, ResponseRouter.directoryHasIndex(real));
    }
}