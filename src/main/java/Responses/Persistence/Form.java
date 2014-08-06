package Responses.Persistence;

public class Form {
    private String data, url;

    public Form(String data){
        this.url = "/form";
        this.data = data;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }
}
