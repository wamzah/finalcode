package com.example.dicdog1;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class SearchingByTime extends ActionBarActivity {
	Button btnSelectDate,btnSelectTime;
    
    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID=1;
    
        // variables to save user selected date and time
    public  int year,month,day,hour,minute;  
// declare  the variables to Show/Set the date and time when Time and  Date Picker Dialog first appears
    private int mYear, mMonth, mDay,mHour,mMinute; 
    
    // constructor
    
    public SearchingByTime()
    {
                // Assign current Date and Time Values to Variables
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
    }
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//hide action bar
		ActionBar actionBar=getSupportActionBar();
		actionBar.hide();
		setContentView(R.layout.activity_searching_by_time);
		//speciality spinner
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		List<String> jobspec=new ArrayList<String>();
  		DatabaseHandler db=new DatabaseHandler(this);
        db.getReadableDatabase();
        jobspec=db.getJobList();
        db.close();
		//array adapter for adding string
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_item_text, jobspec);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter( new NothingSelectedSpinnerAdapter(adapter, R.layout.contact_spinner_row_nothing_selected,this));
	    
	  //adding second gender spinner
  		Spinner genderspinner = (Spinner) findViewById(R.id.Spinner01);
  		String genderspec[]={"male","female","both"};
  		//array adapter for adding string
  		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,R.layout.spinner2_item_text,genderspec);
  	    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  	    //calling nothingclass for setting the default value on spinner
  	    genderspinner.setAdapter( new NothingSelectedSpinnerAdapter(adapter2, R.layout.contact_spinner2_row_nothing_selected,this));
	    
  	    //date and time picker process
  	// get the references of buttons
        btnSelectDate=(Button)findViewById(R.id.buttondate);
        btnSelectTime=(Button)findViewById(R.id.buttontime);
        
        // Set ClickListener on btnSelectDate 
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                // Show the DatePickerDialog
                 showDialog(DATE_DIALOG_ID);
            }
        });
        
        // Set ClickListener on btnSelectTime
        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                // Show the TimePickerDialog
                 showDialog(TIME_DIALOG_ID);
            }
        });
        
}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searching_by_time, menu);
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
	// Register  DatePickerDialog listener //a complete dialog for selecting date and time
    private DatePickerDialog.OnDateSetListener mDateSetListener =
                           new DatePickerDialog.OnDateSetListener() {
                       // the callback received when the user "sets" the Date in the DatePickerDialog
                               public void onDateSet(DatePicker view, int yearSelected,
                                                     int monthOfYear, int dayOfMonth) {
                                  year = yearSelected;
                                  month = monthOfYear;
                                  day = dayOfMonth;
                                  SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
                                  Date d = new Date();
                                  String dayOfTheWeek = sdf.format(d);
                                  
                               }
                           };

      // Register  TimePickerDialog listener                 
                           private TimePickerDialog.OnTimeSetListener mTimeSetListener =
                               new TimePickerDialog.OnTimeSetListener() {
                        // the callback received when the user "sets" the TimePickerDialog in the dialog
                                   public void onTimeSet(TimePicker view, int hourOfDay, int min) {
                                       hour = hourOfDay;
                                       minute = min;
                                       Date d=new Date();
                                       String delegate = "hh:mm aaa"; 
                                       SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
                                       updateTime(hour,minute);
                                       
                                     }
                               };


   // Method automatically gets Called when you call showDialog()  method
                           @Override
                           protected Dialog onCreateDialog(int id) {
                               switch (id) {
                               case DATE_DIALOG_ID:
                        // create a new DatePickerDialog with values you want to show 
                                   return new DatePickerDialog(this,
                                               mDateSetListener,
                                               mYear, mMonth, mDay);
                       // create a new TimePickerDialog with values you want to show 
                               case TIME_DIALOG_ID:
                                   return new TimePickerDialog(this,
                                           mTimeSetListener, mHour, mMinute, false);
                              
                               }
                               return null;
                           }
                           private static String utilTime(int value) {
                               
                               if (value < 10)
                                   return "0" + String.valueOf(value);
                               else
                                   return String.valueOf(value);
                           }
                            
                           // Used to convert 24hr format to 12hr format with AM/PM values
                           private void updateTime(int hours, int mins) {
                                
                               String timeSet = "";
                               if (hours > 12) {
                                   hours -= 12;
                                   timeSet = "PM";
                               } else if (hours == 0) {
                                   hours += 12;
                                   timeSet = "AM";
                               } else if (hours == 12)
                                   timeSet = "PM";
                               else
                                   timeSet = "AM";
                        
                                
                               String minutes = "";
                               if (mins < 10)
                                   minutes = "0" + mins;
                               else
                                   minutes = String.valueOf(mins);
                        
                               // Append in a StringBuilder
                                String aTime = new StringBuilder().append(hours).append(':')
                                       .append(minutes).append(" ").append(timeSet).toString();
                               Toast.makeText(getApplicationContext(), aTime, Toast.LENGTH_LONG).show();
                                
                           }
                       
}
