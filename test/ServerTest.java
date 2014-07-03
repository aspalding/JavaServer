import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ServerTest {
    Server server;

    @Before
    public void setUp() throws Exception{
        server = new Server();
    }

    @After
    public void tearDown() throws Exception{
        server.getServerSocket().close();
        server = null;
    }

    @Test
    public void testParseArgumentsPort() throws Exception {
        String[] cat = {"-p", "8080"};
        List<String> args = Arrays.asList(cat);

        server.parseArguments(args);

        assertEquals(server.port, 8080);
    }

    @Test
    public void testParseArgumentsRoot() throws Exception {
        String[] cat = {"-d", "/home/andrew"};
        List<String> args = Arrays.asList(cat);

        server.parseArguments(args);

        assertEquals(server.root, "/home/andrew");
    }

    @Test
    public void testParseArgumentsBoth() throws Exception {
        String[] cat = {"-p", "8080", "-d", "/home/andrew"};
        List<String> args = Arrays.asList(cat);

        server.parseArguments(args);

        assertEquals(server.port, 8080);
        assertEquals(server.root, "/home/andrew");
    }

    @Test
    public void testParseArgumentsInvalid() throws Exception {
        String[] cat = {"-p", "-d"};
        List<String> args = Arrays.asList(cat);

        server.parseArguments(args);

        assertEquals(server.port, 4000);
    }
}