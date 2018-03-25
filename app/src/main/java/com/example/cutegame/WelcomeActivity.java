package com.example.cutegame;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class WelcomeActivity extends ActionBarActivity {
    TextView tv;
    Button nextBTN;
    RadioGroup rgOperation;
    RadioGroup rgMode;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		tv = (TextView)findViewById(R.id.TVusername);
		
		rgOperation = (RadioGroup)findViewById(R.id.RGOperation);
		rgMode = (RadioGroup)findViewById(R.id.RGMode);

        String username = getIntent().getStringExtra("Username");
        tv.setText("Welcome, " + username); 
        
        nextBTN = (Button) findViewById(R.id.BTNNext);

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	int radioButtonID = rgMode.getCheckedRadioButtonId();
            	View radioButton = rgMode.findViewById(radioButtonID);
            	int idxMode = rgMode.indexOfChild(radioButton);
            	RadioButton r = (RadioButton)rgMode.getChildAt(idxMode);
            	String selectedtextMode = (String)r.getText();
            	
            	int radioButtonID1 = rgOperation.getCheckedRadioButtonId();
            	View radioButton1 = rgOperation.findViewById(radioButtonID1);
            	int idxOperation = rgOperation.indexOfChild(radioButton1);
            	RadioButton r1 = (RadioButton)rgOperation.getChildAt(idxOperation);
            	String selectedtextOperation = (String)r1.getText();
            	
            	Spinner mySpinner=(Spinner) findViewById(R.id.spinner1);
            	String text = mySpinner.getSelectedItem().toString();
            	
                Intent i = new Intent(WelcomeActivity.this, GameActivity.class);
                i.putExtra("Mode", selectedtextMode);
                i.putExtra("Operation", selectedtextOperation);
                i.putExtra("rowNbr", text);
                startActivity(i);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
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
}
