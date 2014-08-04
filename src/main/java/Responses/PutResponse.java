package Responses;

public class PutResponse extends ResponseObj{
    String requestBody, requestPath;

    public PutResponse(String requestBody, String requestPath) {
        this.requestBody = requestBody;
        this.requestPath = requestPath;
        this.generateStatusAndReason();
        this.generateContentTypeHeader();
        this.generateBody();
    }

    private void generateStatusAndReason() {
        if(isAllowed(requestPath)) {
            this.status = 200;
            this.reason = "OK";
        } else {
            this.status = 405;
            this.reason = "Method Not Allowed";
        }
    }

    private void generateContentTypeHeader() {
        this.headers.put("Content-Type", "text/html");
    }

    private void generateBody() {
        this.body = requestBody.getBytes();
    }

    public boolean isAllowed(String path) {
        return !path.contains("file1");
    }
}
