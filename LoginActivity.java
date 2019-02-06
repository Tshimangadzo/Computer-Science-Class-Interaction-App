package com.example.assignment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener,
		async_int {
	EditText username, password;
	Button ok, cancel;
	String user_name;
	String user_pass, text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		username = (EditText) findViewById(R.id.editText1);
		cancel = (Button) findViewById(R.id.cance);
		password = (EditText) findViewById(R.id.editText2);
		ok = (Button) findViewById(R.id.Sub);
		cancel.setOnClickListener(this);
		ok.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {

		user_name = username.getText().toString();
		user_pass = password.getText().toString();
		List<NameValuePair> nams = new ArrayList<NameValuePair>();
		nams.add(new BasicNameValuePair("username", user_name));
		nams.add(new BasicNameValuePair("password", user_pass));

		if (arg0.getId() == R.id.Sub) {
			if (user_name.trim().equals("") || user_pass.trim().equals("")) {
				Toast.makeText(this, "Enter a Username and Password",
						Toast.LENGTH_SHORT).show();
			}

			new AsyncHttpPost(this).execute(
					"http://lamp.ms.wits.ac.za/~800361/isValidLogin.php", nams);
		}

		if (arg0.getId() == R.id.cance) {
			Intent j = new Intent(this, MainActivity.class);
			startActivity(j);
		}

	}
	@Override
	public void onpost(String h) {
		if (h.equals("username already exists")) {
			Intent k = new Intent(LoginActivity.this, Question1Activity.class);
			Bundle bundle = new Bundle();
			bundle.putString("user_name", username.getText().toString());
			k.putExtras(bundle);
			startActivity(k);
			STORE.user_name = username.getText().toString();

		} else {
			Toast.makeText(LoginActivity.this, h, Toast.LENGTH_SHORT).show();
		}
	}
}
