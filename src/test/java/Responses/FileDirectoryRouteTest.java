package Responses;

import Requests.Request;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileDirectoryRouteTest {
    Request request, rangeRequest, putRequest, postRequest, directoryRequest;
    FileDirectoryRoute route, rangeRoute, putRoute, postRoute, directoryRoute;

    @Before
    public void setUp() throws Exception {
        request = new Request(
                "GET /pom.xml HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );
        route = new FileDirectoryRoute(request);

        rangeRequest = new Request(
                "GET /pom.xml HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Range: bytes=0-4\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );
        rangeRoute = new FileDirectoryRoute(rangeRequest);

        putRequest = new Request(
                "PUT /pom.xml HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );
        putRoute = new FileDirectoryRoute(putRequest);

        postRequest = new Request(
                "POST /pom.xml HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );
        postRoute = new FileDirectoryRoute(postRequest);

        directoryRequest = new Request(
                "GET / HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );
        directoryRoute = new FileDirectoryRoute(directoryRequest);
    }

    @Test
    public void testRespond() throws Exception {
        assert route.respond().status == 200;
        assert rangeRoute.respond().status == 206;
        assert putRoute.respond().status == 200;
        assert postRoute.respond().status == 200;

        Request request = new Request(
                "OPTION / HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );
        Route route = new FileDirectoryRoute(request);
        assert route.respond().status == 405;
    }

    @Test
    public void testGet() throws Exception {
        assert route.get().status == 200;

    }

    @Test
    public void testPut() throws Exception {
        assert route.put() != null;
    }

    @Test
    public void testPost() throws Exception {
        assert route.post() != null;
    }

    @Test
    public void testGenerateHeaders() throws Exception {
        assert route.generateHeaders().containsValue("application/xml");
    }

    @Test
    public void testGenerateBody() throws Exception {
        assert new String(directoryRoute.generateBody()).contains("li");
    }

    @Test
    public void testFileToBytes() throws Exception {
        assert route.fileToBytes(request.path).length != 0;
    }

    @Test
    public void testFileToBytesWithRange() throws Exception {
        assert route.fileToBytes(request.path, 4).length == 4;
    }

    @Test
    public void testSadFileToBytes() throws Exception {
        Request request = new Request(
                "GET /virus.exe HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );
        FileDirectoryRoute route = new FileDirectoryRoute(request);
        assert route.fileToBytes(request.path) == null;
        assert route.fileToBytes(request.path, 4) == null;
    }

    @Test
    public void testIsAllowed() throws Exception {
        Request request = new Request(
                "PUT /file1 HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );
        FileDirectoryRoute route = new FileDirectoryRoute(request);
        assert !route.isAllowed("file1");

        request = new Request(
                "POST /text-file.txt HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );
        route = new FileDirectoryRoute(request);
        assert !route.isAllowed("text-file.txt");
    }
}