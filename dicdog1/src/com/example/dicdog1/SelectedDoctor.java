package com.example.dicdog1;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class SelectedDoctor extends ActionBarActivity {

	private static Button icon;
	private static Button call;
	private static TextView main;
	private static TextView name;
	private static TextView spec;
	private static TextView gender;
	private static TextView hospital;
	private static TextView timings;
	private static TextView experience;
	private static EditText edit1;
	
	
	private static Doctor doc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar=getSupportActionBar();
		actionBar.hide();
		setContentView(R.layout.activity_selected_doctor);		
		/*Button home=(Button) findViewById(R.id.homebitton);
		home=(Button)findViewById(R.id.button2);
		home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(SelectedDoctor.this,DashboardActivity.class);
				startActivity(i);
			}
		});*/
		//call = (Button)findViewById(R.id.button2);
		name=(TextView)findViewById(R.id.textView1);
		spec=(TextView)findViewById(R.id.textView3);		
		gender=(TextView)findViewById(R.id.TextView02);
		hospital=(TextView)findViewById(R.id.textView8);
		timings=(TextView)findViewById(R.id.textView9);
		experience=(TextView)findViewById(R.id.textView10);
		icon = (Button)findViewById(R.id.button1);
		Intent intent=getIntent();
		String selectedValue=intent.getExtras().getString("doctor");
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("DoctorsTable");
		query.whereEqualTo("Name", selectedValue);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> la,
					com.parse.ParseException e) {
				// TODO Auto-generated method stub
				 if(la!=null){
					 name.setText(la.get(0).getString("Name"));
						spec.setText(la.get(0).getString("Job"));
						gender.setText(la.get(0).getString("Gender"));
						hospital.setText(la.get(0).getString("Hospital"));
						experience.setText("9-5");
						timings.setMovementMethod(new ScrollingMovementMethod());
						timings.setText(la.get(0).getString("Qualifications"));
						if(la.get(0).getString("Gender").equals(("male")))
						{
							icon.setBackgroundResource(R.drawable.doctor);
						}
						else if(la.get(0).getString("Job").equals(("female")))
						{
							icon.setBackgroundResource(R.drawable.doctor);
						}

					
                         }
                  else{//handle the error}
                            }
				
			}
		});
		
		/*if(doc.gender.equals("male"))
		{
			icon.setBackgroundResource(R.drawable.doctor);
		}
		else if(doc.gender.equals("female"))
		{
			icon.setBackgroundResource(R.drawable.doctor);
		}
		icon.setClickable(false);
		/*call.setBackgroundResource(R.drawable.doctor);
		call.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {				
				// TODO Auto-generated method stub
				String num="";
		        num=doc.getphone();
		        String number = "tel:" + num.trim();				
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
			    startActivity(intent);
			}
		});*/
		
		//Toast.makeText(this, doc.getname(), Toast.LENGTH_SHORT).show();
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selected_doctor, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
}
