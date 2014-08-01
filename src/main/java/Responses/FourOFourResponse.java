package Responses;

public class FourOFourResponse extends ResponseObj {
    public FourOFourResponse(){
        this.generateStatusAndReason();
        this.generateContentTypeHeader();
        this.generateBody();
    }

    private void generateStatusAndReason() {
        this.status = 404;
        this.reason = "Not Found";
    }

    private void generateContentTypeHeader() {
        this.headers.put("Content-Type", "text/html");
    }

    private void generateBody() {
        this.body = "Page Not Found.".getBytes();
    }
}