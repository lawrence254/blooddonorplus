package lawrence.blooddonor;

/**
 * Created by EliteBook on 7/18/2017.
 */

public class ApplicationConstants {
	public static final String URL_ADD = "https://grazed-scene.000webhostapp.com/bdp/add.php";
	public static final String URL_CARD = "https://grazed-scene.000webhostapp.com/bdp/card.php?id=";

	public static final String URL_GET_ALL = "http://192.168.137.1/Android/CRUD/getAllEmp.php";
	public static final String URL_GET_EMP = "http://192.168.137.1/Android/CRUD/getEmp.php?id=";
	public static final String URL_UPDATE_EMP = "http://192.168.137.1/Android/CRUD/updateEmp.php";
	public static final String URL_DELETE_EMP = "http://192.168.137.1/Android/CRUD/deleteEmp.php?id=";


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
