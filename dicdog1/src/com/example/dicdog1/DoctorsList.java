package com.example.dicdog1;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class DoctorsList extends ListActivity {

private static List<String> jobList;
private static List<String> nameList;
private static List<String> genderList;
private static List<String> genderList1;
//private static String s1;
private static String selectedValue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_doctor_list);
		jobList =new ArrayList<String>();
		 nameList =new ArrayList<String>();
		 genderList =new ArrayList<String>(); 
		 //genderList1 =new ArrayList<String>();
		 List<Doctor> doctorList =new ArrayList<Doctor>();
		Intent intent=getIntent();
		String speciality=intent.getExtras().getString("speciality");
 		String gender=intent.getExtras().getString("gender");
 		String hosp=intent.getExtras().getString("hospital");
 		
 		//////////////////////////////
 		ParseQuery<ParseObject> query = ParseQuery.getQuery("DoctorsTable");
		query.whereEqualTo("Gender", gender);
		query.whereEqualTo("Job", speciality);
		query.whereEqualTo("Hospital", hosp);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> la,
					com.parse.ParseException e) {
				
				// TODO Auto-generated method stub
				 if(la!=null){
					 
					 for(int i=0;i<la.size()+1;i++)
						{
						 if(i==la.size())
						 {
							 String s0="end";
								String s2="end";
								String s="end";
								check(s0,s,s2);
								Toast.makeText(getApplicationContext(), "hello1 ", Toast.LENGTH_LONG).show();
								break;
						 }
						 else
						 {
					 Toast.makeText(getApplicationContext(),la.get(i).getString("Job"), Toast.LENGTH_LONG).show();
						 String s=la.get(i).getString("Job");
						 String s1=la.get(i).getString("Name");
						 String s2=la.get(i).getString("Gender");
						 check(s1,s,s2);
					 Toast.makeText(getApplicationContext(),"Hello " + s, Toast.LENGTH_LONG).show();
			//		     genderList1.add(s);		
						 }};
						
						//Toast.makeText(getApplicationContext(),"Hello " + genderList1.get(0), Toast.LENGTH_LONG).show();
					
                         }
                  else{//handle the error}
                	  Toast.makeText(getApplicationContext(), "hello2", Toast.LENGTH_LONG).show();
                            }
				
			}
		});
		
		
		
 		//////////////////////////////
 		/*DatabaseHandler db=new DatabaseHandler(this);
		db.getWritableDatabase();
		doctorList=db.getAllContacts(speciality,gender,hosp); 
		db.close(); 		 					
		for(int i=0;i<doctorList.size();i++)
		{
			nameList.add(doctorList.get(i).getname());
			jobList.add("\n"+doctorList.get(i).getjob());
			genderList.add(doctorList.get(i).getgender());
		}
		//jobList=db.getJobList();
		
		setListAdapter(new DoctorListAdapter(this, nameList,jobList,genderList));*/
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {

		//get selected items
		//String selectedValue = (String) getListAdapter().getItem(position);
		selectedValue = (String) getListAdapter().getItem(position);		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("DoctorsTable");
		query.whereEqualTo("Name", selectedValue);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> la,
					com.parse.ParseException e) {
				// TODO Auto-generated method stub
				 if(la!=null){
					 Intent intent=new Intent(DoctorsList.this,SelectedDoctor.class);
					 intent.putExtra("doctor", selectedValue);
				     startActivity(intent);
					 
					
                         }
                  else{//handle the error}
                            }
				
			}
		});
		/*DatabaseHandler db=new DatabaseHandler(this);
		db.getWritableDatabase();
		Doctor doc=db.getContact(selectedValue);
		//db.close();
		Intent intent=new Intent(DoctorsList.this,SelectedDoctor.class);
		Bundle bun=new Bundle();
    	bun.putParcelable("doctor", doc);
    	intent.putExtras(bun);
    	Toast.makeText(this, doc.getname(), Toast.LENGTH_SHORT).show();
    	this.startActivity(intent);
    	
		*/
		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
 
	}
	public void check(String s1,String s,String s2)
	{
		 
		if(s1.equals("end"))
		{
			Toast.makeText(getApplicationContext(),"Ending", Toast.LENGTH_LONG).show();
			setListAdapter(new DoctorListAdapter(this, nameList,jobList));
		}
		else
		{
			//Toast.makeText(getApplicationContext(),"Hello YESH"  + s2, Toast.LENGTH_LONG).show();
			nameList.add(s1);
			jobList.add("\n\n" + s);
			genderList.add(s2);
			Toast.makeText(getApplicationContext(), "helloeyeyyy ", Toast.LENGTH_LONG).show();
		}
	}

}
