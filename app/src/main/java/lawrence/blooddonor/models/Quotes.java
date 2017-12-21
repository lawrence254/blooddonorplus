package lawrence.blooddonor.models;

/**
 * Created by ELITEBOOK on 21/12/2017.
 */

public class Quotes {
    private int id;
    private String fact,source;

    public Quotes(int id,String fact, String source){
        //this.id=id;
        this.fact=fact;
        this.source = source;
    }
//    public int getId(){
//        return id;
//    }
    public String getFact(){
        return fact;
    }
    public String getSource(){
        return source;
    }
}
