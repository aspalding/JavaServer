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
                "Cache-Control: max-age=0\r\n\r\n" +
                "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(indexReq) instanceof FileResponse));

        Request dirReq = new Request(
                "GET /src HTTP/1.1\n" +
                        "Host: localhost:4000\n" +
                        "Connection: keep-alive\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(dirReq) instanceof DirectoryResponse));

        Request fileReq = new Request(
                "GET /src/Application.java HTTP/1.1\n" +
                        "Host: localhost:4000\n" +
                        "Connection: keep-alive\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(fileReq) instanceof FileResponse));

        Request postReq = new Request(
                "POST /form HTTP/1.1\n" +
                        "Host: localhost:4000\n" +
                        "Connection: keep-alive\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(postReq) instanceof PostResponse));

        Request optReq = new Request(
                "OPTIONS /method_options HTTP/1.1\n" +
                        "Host: localhost:4000\n" +
                        "Connection: keep-alive\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(optReq) instanceof OptionsResponse));

        Request invalid = new Request(
                "GET C: HTTP/1.1\n" +
                        "Host: localhost:4000\n" +
                        "Connection: keep-alive\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(invalid) instanceof FourOFourResponse));

        Request partial = new Request(
                "GET /src/Application.java HTTP/1.1\n" +
                        "Host: localhost:4000\n" +
                        "Connection: keep-alive\n" +
                        "Range: bytes=0-4\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(partial) instanceof PartialResponse));

        Request parameters = new Request(
                "GET /parameters?variable_1=sadf HTTP/1.1\n" +
                        "Host: localhost:4000\n" +
                        "Connection: keep-alive\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(parameters) instanceof GetParameterResponse));
    }

    @Test
    public void testDirectoryHasIndex() throws Exception{
        File real = new File(System.getProperty("user.dir"));
        assertEquals(true, ResponseRouter.directoryHasIndex(real));
    }

    @Test
    public void testRedirectRoot() throws Exception {
        String path = "/blah/redirect";
        assertEquals(true, ResponseRouter.requestRedirectRoot(path));
    }

    @Test
    public void testRedirectRootRoute() throws Exception {
        Request redirect = new Request(
                "GET /redirect HTTP/1.1\n" +
                        "Host: localhost:4000\n" +
                        "Connection: keep-alive\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );

        assertEquals(true, (ResponseRouter.route(redirect) instanceof RedirectResponse));
    }
}