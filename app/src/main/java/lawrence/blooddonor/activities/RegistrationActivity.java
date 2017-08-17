package lawrence.blooddonor.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lawrence.blooddonor.R;
import lawrence.blooddonor.RequsetHandler;
import lawrence.blooddonor.ApplicationConstants;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

	// [END declare_database_ref]
	public EditText eFname;
	public EditText eLname;
	public EditText phone;
	public Spinner sType;
	public Spinner sGender;
	public Button bRegister;
	// [START declare_database_ref]
	private DatabaseReference mDatabase;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		// [START initialize_database_ref]
		mDatabase = FirebaseDatabase.getInstance().getReference();

		progressDialog = new ProgressDialog(this);
		// [END initialize_database_ref]
	    /*Gender Radio Group Initialization*/

		//SignUp Initialization

		eFname = (EditText)findViewById(R.id.eFname);
		eLname = (EditText)findViewById(R.id.eLname);
		phone = (EditText)findViewById(R.id.ePhoneNumber);
		sType = (Spinner)findViewById(R.id.sType);
		sGender = (Spinner)findViewById(R.id.sGender);
		bRegister = (Button)findViewById(R.id.bRegister);

		bRegister.setOnClickListener(this);

        /*Start Blood Type Spinner*/
		List<String> spinnerarray = new ArrayList<>();
		spinnerarray.add("Tap to select your blood group");
		spinnerarray.add("O+");
		spinnerarray.add("O-");
		spinnerarray.add("A+");
		spinnerarray.add("A-");
		spinnerarray.add("B+");
		spinnerarray.add("B-");
		spinnerarray.add("AB+");
		spinnerarray.add("AB-");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerarray);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner sType = (Spinner)findViewById(R.id.sType);
		sType.setAdapter(adapter);
        /*End Blood Type Spinner*/

        /*Start Gender Spinner*/
		List<String> spinnerg = new ArrayList<>();
		spinnerg.add("Tap to select your Gender");
		spinnerg.add("Female");
		spinnerg.add("Male");

		ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerg);

		adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner sGender = (Spinner)findViewById(R.id.sGender);
		sGender.setAdapter(adapt);
        /*End Gender Spinner*/

	}

	//Adding an employee
	private void addEmployee() {

		final String fname = eFname.getText().toString().trim();
		final String lname = eLname.getText().toString().trim();

		final String sex = sGender.getSelectedItem().toString().trim();
		final String blood = sType.getSelectedItem().toString().trim();
		final String call = phone.getText().toString().trim();
		class AddEmployee extends AsyncTask<Void, Void, String> {

			ProgressDialog loading;

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				loading = ProgressDialog.show(RegistrationActivity.this, "Adding...", "Wait...", false, false);
			}

			@Override
			protected void onPostExecute(String s) {
				super.onPostExecute(s);
				if (loading != null) {
					loading.dismiss();
					Toast.makeText(RegistrationActivity.this, s, Toast.LENGTH_LONG).show();

				}
				startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
				finish();

			}

			@Override
			protected String doInBackground(Void... v) {
				HashMap<String, String> params = new HashMap<>();
				params.put(ApplicationConstants.KEY_Last_Name, lname);
				params.put(ApplicationConstants.KEY_First_Name, fname);
				params.put(ApplicationConstants.KEY_Gender, sex);
				params.put(ApplicationConstants.KEY_Blood, blood);
				params.put(ApplicationConstants.KEY_Phone, call);
				RequsetHandler rh = new RequsetHandler();
				String res = rh.sendPostRequest(ApplicationConstants.URL_ADD, params);
				return res;
			}
		}

		AddEmployee ae = new AddEmployee();
		ae.execute();
	}

	@Override
	public void onClick(View v) {

		if (v == bRegister) {
			addEmployee();
		}
	}
}


