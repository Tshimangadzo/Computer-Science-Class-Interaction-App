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

public class RegisterActivity extends Activity implements OnClickListener,async_int {
	Button submit;
	EditText USER_NAME,CONF_PASS,USER_PASS;
	String user_name,user_pass,conf_pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		submit = (Button)findViewById(R.id.sub);
		USER_NAME = (EditText)findViewById(R.id.user1);
		USER_PASS =(EditText)findViewById(R.id.password1);
		CONF_PASS =(EditText)findViewById(R.id.password2);
		submit.setOnClickListener(this);
	}
	@SuppressWarnings("unused")
	@Override
	public void onClick(View arg0) {
		user_name = USER_NAME.getText().toString();
	    user_pass = USER_PASS.getText().toString();
		conf_pass = CONF_PASS.getText().toString();
		List<NameValuePair> nams = new ArrayList<NameValuePair>();
		nams.add(new BasicNameValuePair("username", user_name));
		nams.add(new BasicNameValuePair("password", user_pass));
		if(USER_NAME.getText().toString().equals("")||USER_PASS.getText().toString().equals("")){
			Toast.makeText(getBaseContext(), "Enter valid Username and Password", Toast.LENGTH_SHORT).show();
			
		}
		else{
		if (USER_PASS.getText().toString().equals(CONF_PASS.getText().toString()) && USER_PASS.getText().toString() !="" ){
			TextView e = (TextView)findViewById(R.id.textView1);
			new AsyncHttpPost(this).execute("@server",nams);
			
		}
		else {
			Toast.makeText(getBaseContext(), "Registration  failed, Password not matching", Toast.LENGTH_SHORT).show();
			CONF_PASS.setText("");
		}
	}
	
	}
	@Override
	public void onpost(String h) {
		// TODO Auto-generated method stub
		Toast.makeText(RegisterActivity.this, h, Toast.LENGTH_SHORT).show();
		
		
	}

}
