import java.util.HashMap;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class Request {
    StringTokenizer st;
    HashMap<String, String> headers;
    String request, method, path, body;

    public Request(String request){
        this.request = request;

        this.headers = new HashMap<String, String>();
        this.st = new StringTokenizer(this.request);

        this.method = tokenizeMethod();
        this.path = tokenizePath();
        this.headers = tokenizeHeaders();

        if(st.hasMoreTokens())
            this.body = tokenizeBody();
    }

    public String tokenizeMethod(){
        return st.nextToken();
    }

    public String tokenizePath(){
        return System.getProperty("user.dir") + st.nextToken();
    }

    public HashMap<String, String> tokenizeHeaders(){
        st.nextToken(); //Discard HTTP Version.
        HashMap<String, String> ht = new HashMap<String, String>();
        do {
            String key = st.nextToken();
            String value = st.nextToken();
            ht.put(key.substring(0, key.length() - 1), value);

        } while (st.countTokens() != 1 && st.countTokens() != 0);

        return ht;
    }

    public String tokenizeBody(){
        return st.nextToken();
    }

}