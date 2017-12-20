package lawrence.blooddonor;

/**
 * Created by EliteBook on 7/18/2017.
 */

public class ApplicationConstants {
	public static final String URL_ADD = "https://bdplus.000webhostapp.com/add.php";
	public static final String URL_CARD = "https://bdplus.000webhostapp.com/card.php?id=";

	//Keys that will be used to send the request to php scripts

	public static final String KEY_First_Name = "fname";
	public static final String KEY_Last_Name = "lname";
	public static final String KEY_Gender = "sex";
	public static final String KEY_Blood = "blood";
	public static final String KEY_Phone = "call";
	public static final String KEY_Hosp = "hospName";
	public static final String KEY_Contact = "contact";

	//JSON Tags
	public static final String TAG_JSON_ARRAY = "result";
	public static final String TAG_Fname = "fname";
	public static final String TAG_Lname = "lname";
	public static final String TAG_Gender = "sex";
	public static final String TAG_Blood = "blood";
	public static final String TAG_Phone = "call";
	public static final String TAG_Hosp = "hospName";
	public static final String TAG_Contact = "contact";

	//employee id to pass with intent
	public static final String Hosp_ID = "id";
}
