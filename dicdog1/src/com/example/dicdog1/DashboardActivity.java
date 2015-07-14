package com.example.dicdog1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

public class DashboardActivity extends ActionBarActivity {
private static Button button1;
private static Intent i;
private static Button buttontime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar=getSupportActionBar();
		bar.hide();
		setContentView(R.layout.activity_dashboard);
		overridePendingTransition(R.layout.rotate_out, R.layout.rotatein);
		//search by doctor button click
		Toast.makeText(getApplicationContext(), Landingpage.hospital_list.get(0), Toast.LENGTH_LONG).show();
	
		button1=(Button)findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=new Intent(DashboardActivity.this,DoctorsActivity.class);
				 
				startActivity(i);
		      
			}
		});
		buttontime=(Button)findViewById(R.id.buttontime);
		buttontime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=new Intent(DashboardActivity.this,SearchingByTime.class);
				 
				startActivity(i);
		      
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
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
	}
	public Animation inFromRightAnimation()
	{
	    Animation inFromRight = new TranslateAnimation(
	            Animation.RELATIVE_TO_PARENT, +1.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f);
	    inFromRight.setDuration(240);
	    inFromRight.setInterpolator(new AccelerateInterpolator());
	    return inFromRight;
	}
	public Animation outToRightAnimation()
	{
	    Animation outtoLeft = new TranslateAnimation(
	            Animation.RELATIVE_TO_PARENT, -1.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f);
	    outtoLeft.setDuration(240);
	    outtoLeft.setInterpolator(new AccelerateInterpolator());
	    return outtoLeft;
	}
}
