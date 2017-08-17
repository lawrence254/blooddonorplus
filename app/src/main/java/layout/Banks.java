package layout;

/**
 * Created by EliteBook on 8/15/2017.
 */
public class Banks {
    private String hospName,contact;
    private Integer id;

   public Banks(Integer id,String hospName,String contact) {
       this.id=id;
       this.hospName = hospName;
       this.contact = contact;
   }
    public int getId() {
        return id;
    }
    public void setId(){
        this.id=id;
    }
    public String getHospName() {
        return hospName;
    }

    public void setHospName(String hospName) {
        this.hospName = hospName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}