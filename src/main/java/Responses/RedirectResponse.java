package Responses;

public class RedirectResponse extends ResponseObj{
    String host;

    public RedirectResponse(String host){
        this.host = host;
        this.generateStatusAndReason();
        this.generateContentTypeHeader();
        this.generateBody();
    }

    private void generateStatusAndReason() {
        this.status = 302;
        this.reason = "Found";
    }

    private void generateContentTypeHeader() {
        this.headers.put("Location", "http://" + host + "/");
    }

    private void generateBody() {
        this.body = "This page has moved.".getBytes();
    }
}