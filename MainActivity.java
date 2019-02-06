package com.example.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener{
	Button login;
	Button Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
       login = (Button)findViewById(R.id.button1);
       Register =(Button)findViewById(R.id.button2);
       login.setOnClickListener(this);
       Register.setOnClickListener(this);
    }
	@Override
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.button1)
		{
			Intent  i = new Intent(this, LoginActivity.class);
			startActivity(i);
		}
		if(arg0.getId() == R.id.button2)
		{
			Intent j = new Intent(this, RegisterActivity.class);
			startActivity(j);
		}

	}
	
	
public class JSONObjectActivity {	    
	   
	}
	
	
	
}








