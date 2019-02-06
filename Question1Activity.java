package com.example.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Question1Activity extends Activity implements OnClickListener, async_int {
	//Button add;
	Button logout,add;
	TextView name;
	EditText edit;
	String quest;
	RadioButton t4;
	String Quest_ID;
	String value ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question1);
		name =(TextView)findViewById(R.id.textView1);
		edit =(EditText)findViewById(R.id.editText09);
		logout =(Button)findViewById(R.id.log);
		add = (Button)findViewById(R.id.add);
		logout.setOnClickListener(this);
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				quest= edit.getText().toString();
				List<NameValuePair> nams = new ArrayList<NameValuePair>();
				nams.add(new BasicNameValuePair("Question", quest));
				
				name =(TextView)findViewById(R.id.textView1);
				String value =getIntent().getExtras().getString("user_name");
				name.setText(value);
				
				nams.add(new BasicNameValuePair("username",value));
				
				if(quest.equals("")){
					Toast.makeText(Question1Activity.this, "Question cannot be empty", Toast.LENGTH_LONG).show();
				}
				else{
					Toast.makeText(Question1Activity.this, "Question has been uploaded", Toast.LENGTH_LONG).show();
					new AsyncHttpPost(Question1Activity.this).execute("http://lamp.ms.wits.ac.za/~800361/addQuestion.php",nams);
					edit.setText("");
				}
				
			}
		});
		value = getIntent().getExtras().getString("user_name");
		name.setText(value);
		new AsyncHttpPost(this).execute("http://lamp.ms.wits.ac.za/~800361/listQuestions.php",new ArrayList<NameValuePair>());

	}

	public void onpost(String json){
	    	LinearLayout mainLayout = (LinearLayout)findViewById(R.id.show);
	    	LayoutInflater myInflater = getLayoutInflater();
	    	try{
				JSONArray all = new JSONArray(json);
				for (int i=0; i<all.length(); i++){
		    		LinearLayout ll = (LinearLayout)myInflater.inflate(R.layout.inflater, null);
		    		org.json.JSONObject item=all.getJSONObject(i);
					final String Quest_ID = item.getString("Quest_ID");
					String username = item.getString("username");
					String Question = item.getString("Question");
					TextView t1 = (TextView)ll.findViewById(R.id.te);
					TextView t2 = (TextView)ll.findViewById(R.id.user);
					TextView t3 = (TextView)ll.findViewById(R.id.listQuest);
					t4 =(RadioButton)ll.findViewById(R.id.rad);
					t1.setText(Quest_ID);
					t2.setText(username);
					t3.setText(Question);
					t4.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							String Q_ID = Quest_ID;
							List<NameValuePair> nams1 = new ArrayList<NameValuePair>();	
							nams1.add(new BasicNameValuePair("Quest_ID", Q_ID));
							nams1.add(new BasicNameValuePair("username", value));
							new AsyncHttpPost(Question1Activity.this).execute("http://lamp.ms.wits.ac.za/~800361/voteQuestion.php",nams1);
							//Toast.makeText(getApplicationContext(), value, Toast.LENGTH_LONG).show();
						}
					});
					//t5.setOnClickListener(this);
					ll.setTag(item.getString("Quest_ID"));
					ll.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							
							Intent j = new Intent(Question1Activity.this,QuestionActivity.class);
						    STORE.quest_id= Quest_ID;
						    j.putExtra("Quest_ID",v.getTag().toString());
						     startActivity(j);
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
	// TODO Auto-generated method stub
	
}


}
