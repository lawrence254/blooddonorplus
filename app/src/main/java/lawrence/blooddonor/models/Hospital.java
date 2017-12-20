package lawrence.blooddonor.models;

/**
 * Created by EliteBook on 8/1/2017.
 */

public class Hospital {

	private int id,Ap,An,Bp,Bn,ABp,ABn,Op,On;
	private String hospName, contact;

	public Hospital(int id, String hospName, String contact) {
		this.id = id;
		this.hospName = hospName;
		this.Ap = Ap;
		this.Bp = Bp;
		this.ABn = ABn;
		this.Bn = Bn;
		this.On = On;
		this.An = An;
		this.ABp = ABp;
		this.Op = Op;
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

	public int getAp() {
		return Ap;
	}

	public void setAp(int ap) {
		Ap = ap;
	}

	public int getAn() {
		return An;
	}

	public void setAn(int an) {
		An = an;
	}

	public int getBp() {
		return Bp;
	}

	public void setBp(int bp) {
		Bp = bp;
	}

	public int getBn() {
		return Bn;
	}

	public void setBn(int bn) {
		Bn = bn;
	}

	public int getABp() {
		return ABp;
	}

	public void setABp(int ABp) {
		this.ABp = ABp;
	}

	public int getABn() {
		return ABn;
	}

	public void setABn(int ABn) {
		this.ABn = ABn;
	}

	public int getOp() {
		return Op;
	}

	public void setOp(int op) {
		Op = op;
	}

	public int getOn() {
		return On;
	}

	public void setOn(int on) {
		On = on;
	}
//    public static int id=0;
}

