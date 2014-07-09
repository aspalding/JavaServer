import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ResponseTest {

    @Test
    public void testValidFilePathResponseString() throws Exception {
        File fake = new File(""){
            @Override
            public boolean isFile(){
                return true;
            }
        };
        Response resp = new Response(fake);
        assertEquals("HTTP/1.1 200 OK\nContent-Type: null\n\n", resp.responseHeader(200, ""));
    }

    @Test
    public void testValidDirectoryPathResponseString() throws Exception {
        File fake = new File(""){
            @Override
            public boolean isFile(){
                return false;
            }
            @Override
            public boolean isDirectory(){
                return true;
            }
        };
        Response resp = new Response(fake);
        assertEquals("HTTP/1.1 200 OK\nContent-Type: text/html\n\n", resp.responseHeader(200, ""));
    }


    @Test
    public void testInvalidPathResponseString() throws Exception {
        File fake = new File(""){
            @Override
            public boolean isFile(){
                return false;
            }
            @Override
            public boolean isDirectory(){
                return false;
            }
        };
        Response resp = new Response(fake);
        assertEquals("HTTP/1.1 404 Not Found\n\n", resp.responseHeader(200, ""));
    }

    @Test
    public void testFileToBytes() throws Exception {
        File fake = File.createTempFile("fake", "file");

        Response resp = new Response(fake);

        String path = fake.getAbsolutePath();

        assertEquals("", new String(resp.fileToBytes(path)));
    }

    @Test
    public void testResponseBody() throws Exception {
        File fake = File.createTempFile("fake", "file");

        Response resp = new Response(fake);

        assertEquals(new String("".getBytes()), new String(resp.responseBody()));
    }

}