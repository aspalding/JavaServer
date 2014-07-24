package Responses;

public class OptionsResponse extends ResponseObj {
    public OptionsResponse() {
        this.generateStatusAndReason();
        this.generateContentTypeHeader();
        this.generateBody();
    }

    private void generateStatusAndReason() {
        this.status = 200;
        this.reason = "OK";
    }

    private void generateContentTypeHeader() {
        this.headers.put("Content-Type", "text/html");
        this.headers.put("Allow", "GET,HEAD,POST,OPTIONS,PUT");
    }

    private void generateBody() {
        this.body = "".getBytes();
    }
}