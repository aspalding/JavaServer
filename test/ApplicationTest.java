import org.junit.Test;

import java.net.BindException;
import java.net.PortUnreachableException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.*;

public class ApplicationTest {

    @Test(expected = Exception.class)
    public void testMain() throws Exception {
        String[] arr = new String[0];
        Application.main(arr);
        ServerSocket x = new ServerSocket(4000);
    }
}
