package Responses;

public class PostResponse extends ResponseObj {
    String requestBody;

    public PostResponse(String requestBody) {
        this.requestBody = requestBody;
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
    }

    private void generateBody() {
        this.body = requestBody.getBytes();
    }
}