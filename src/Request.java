import java.util.Hashtable;
import java.util.StringTokenizer;

public class Request {
    StringTokenizer st;
    Hashtable<String, String> headers;
    String request, method, path, body;

    public Request(String request){
        this.request = request;

        this.headers = new Hashtable<String, String>();
        this.st = new StringTokenizer(this.request);

        this.method = tokenizeMethod();
        this.path = tokenizePath();
        this.headers = tokenizeRest();

        if(st.hasMoreTokens())
            this.body = tokenizeBody();
    }

    public String tokenizeMethod(){
        return st.nextToken();
    }

    public String tokenizePath(){
        return st.nextToken();
    }

    public Hashtable<String, String> tokenizeRest(){
        Hashtable<String, String> ht = new Hashtable<String, String>();

        st.nextToken();
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