package Responses.Persistence;

import Requests.Request;

import java.io.FileWriter;

public class PatchFile {
    public static final boolean APPEND = false;

    public static void write(Request request){
        try {
            FileWriter fw = new FileWriter(request.path, APPEND);
            fw.write(request.body);
            fw.close();
        } catch(Exception e) {
            //Intentionally blank.
        }
    }
}
