package com.example.assignment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends Activity implements OnClickListener, async_int {
	RadioButton t4;
	Button back,addbu;
	EditText edit;
	TextView name;
	String quest;
	String value;
	String  Q_id;
	String meQuestion;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		meQuestion= i.getStringExtra("Quest_ID");
		setContentView(R.layout.activity_question);
		edit =(EditText)findViewById(R.id.addanswer);
		back =(Button)findViewById(R.id.butt);
		addbu = (Button)findViewById(R.id.addbutton);
		addbu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				quest= edit.getText().toString();
				Intent i = getIntent();
				List<NameValuePair> nams = new ArrayList<NameValuePair>();
				name =(TextView)findViewById(R.id.textView1);
				nams.add(new BasicNameValuePair("username",STORE.user_name));
			    String  meQuestion = i.getStringExtra("Quest_ID");
				nams.add(new BasicNameValuePair("Quest_ID", meQuestion));
				nams.add(new BasicNameValuePair("Answer", quest));
				new AsyncHttpPost(QuestionActivity.this).execute("@server",nams);
				edit.setText("");
				Toast.makeText(QuestionActivity.this, "Answer has been added", Toast.LENGTH_LONG).show();
						
			}
		});
	    back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent k = new Intent(QuestionActivity.this,Question1Activity.class);
				startActivity(k);
				
			}
		});
	    value = i.getStringExtra("user_name");
		List<NameValuePair> nams = new ArrayList<NameValuePair>();
		nams.add(new BasicNameValuePair("Quest_ID", meQuestion));	
		new AsyncHttpPost(this).execute("@server",nams);
	}
	@Override
	public void onpost(String json) {
		//Toast.makeText(getApplicationContext(), json, Toast.LENGTH_LONG).show();
		LinearLayout mainLayout = (LinearLayout)findViewById(R.id.show1);
		LayoutInflater myInflater = getLayoutInflater();
		try{
			JSONArray all = new JSONArray(json);
			for (int i=0; i<all.length(); i++){
				LinearLayout ll = (LinearLayout)myInflater.inflate(R.layout.inflater1, null);
				org.json.JSONObject item=all.getJSONObject(i);
                meQuestion = item.getString("Quest_ID");
				String Answer_ID = item.getString("Answer_ID");
				String username = item.getString("username");
				String Answer = item.getString("Answer");
				TextView t1 = (TextView)ll.findViewById(R.id.id1);
				TextView t2 = (TextView)ll.findViewById(R.id.user1);
				TextView t3 = (TextView)ll.findViewById(R.id.ans);
				t4 =(RadioButton)ll.findViewById(R.id.radio);
				t1.setText(Answer_ID);
				t2.setText(username);
				t3.setText(Answer);
				t4.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						String A_ID = meQuestion;
						List<NameValuePair> nams1 = new ArrayList<NameValuePair>();	
						nams1.add(new BasicNameValuePair("Answer_ID",A_ID));
						nams1.add(new BasicNameValuePair("username", STORE.user_name));
						new AsyncHttpPost(QuestionActivity.this).execute("@server",nams1);
						Toast.makeText(getApplicationContext(), A_ID, Toast.LENGTH_LONG).show();
					}
				});
				ll.setTag(Answer_ID);
				ll.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {

						//Toast.makeText(getApplicationContext(), "You clicked on id "+(String)arg0.getTag(), Toast.LENGTH_SHORT).show();
					}
				});
				mainLayout.addView(ll);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onClick(View arg0) {
		Intent i = getIntent();
		List<NameValuePair> nams = new ArrayList<NameValuePair>();
		nams.add(new BasicNameValuePair("username",STORE.user_name));
	    String  meQuestion = i.getStringExtra("Quest_ID");
		nams.add(new BasicNameValuePair("Quest_ID", meQuestion));
		nams.add(new BasicNameValuePair("Answer", quest));
		new AsyncHttpPost(QuestionActivity.this).execute("@server",nams);
		edit.setText("");	
	}
}

