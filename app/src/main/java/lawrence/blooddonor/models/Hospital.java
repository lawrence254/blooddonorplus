package lawrence.blooddonor.models;

/**
 * Created by EliteBook on 8/1/2017.
 */

public class Hospital {

	private int id;
	private String hospName, contact;

	public Hospital(int id, String hospName, String contact) {
		this.id = id;
		this.hospName = hospName;
		this.contact = contact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	//    public static int id=0;
}

