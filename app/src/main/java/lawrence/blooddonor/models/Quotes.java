package lawrence.blooddonor.models;

/**
 * Created by ELITEBOOK on 21/12/2017.
 */

public class Quotes {
    private int id;
    private String fact,source;

    public Quotes(String fact, String source){

        this.fact=fact;
        this.source = source;
    }
    public String getFact(){
        return fact;
    }
    public String getSource(){
        return source;
    }
}
