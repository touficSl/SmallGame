package com.example.cutegame;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameActivity extends ActionBarActivity {
    Button nextBTN;
    Button BTNRes;
    TextView counterTV;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
//    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        final String Mode = getIntent().getStringExtra("Mode");
        String Operation = getIntent().getStringExtra("Operation");
        String row = getIntent().getStringExtra("rowNbr");


        //default value = Easy
        int rows = Integer.parseInt(row);

        int W = 125;
        init(Mode, Operation, rows, rows, W);

        if (Integer.parseInt(row) != 3)
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        nextBTN = (Button) findViewById(R.id.BTNNext);

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String row = getIntent().getStringExtra("rowNbr");
                int rows = Integer.parseInt(row);

                //check solutions
                CheckSolutions(rows, rows);
            }
        });


        BTNRes = (Button) findViewById(R.id.BTNRes);

        BTNRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String row = getIntent().getStringExtra("rowNbr");
                int rows = Integer.parseInt(row);
                int W = 125;

                counterTV=(TextView)findViewById(R.id.counterTV);
                counterTV.setVisibility(View.INVISIBLE);
                ShowSolutions(rows, rows, W);
            }
        });

        counterTV=(TextView)findViewById(R.id.counterTV);
        counterTV.setVisibility(View.VISIBLE);
        Timer T=new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        counterTV.setText("  count = " + count + " s");
                        count++;
                    }
                });
            }
        }, 1000, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
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


    public void init(String Mode, String Operation, int rows, int cols, int W){
        TableLayout ll = (TableLayout) findViewById(R.id.displayLinear);
        ll.removeAllViews();

        for (int i = 0; i < rows; i++) {	//id= EditText : 0 TextView: 1   => (0/1)i  => 0i ou 1i
            TableRow row= new TableRow(this);
            //TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            //row.setLayoutParams(lp);

            for (int j = 1; j <= cols; j++) {
                String TextViewid = "1" + Integer.toString(i) + Integer.toString(j);    //id= EditText : 0 TextView: 1   => (0/1)ij  => 0ij ou 1ij
                String EditTextid = "0" + Integer.toString(i) + Integer.toString(j);

                EditText et1 = new EditText(this);
                et1.setId(Integer.parseInt(EditTextid));
                et1.setInputType(InputType.TYPE_CLASS_PHONE);
                et1.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                et1.setPadding(5, 5, 5, 5);//left, top, right, bottom
                et1.setWidth(W);
                et1.setTextColor(Color.WHITE);
                et1.setTextSize(30);
                et1.setTypeface(null, Typeface.BOLD);
                et1.setBackgroundResource(R.drawable.backwithborder);

                TextView tv1 = new TextView(this);
                tv1.setVisibility(View.INVISIBLE);
                tv1.setId(Integer.parseInt(TextViewid));
                tv1.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                tv1.setPadding(5, 5, 5, 5);
                tv1.setWidth(20);
                tv1.setTextColor(Color.WHITE);
                tv1.setTextSize(30);
                tv1.setTypeface(null, Typeface.BOLD);
                //tv1.setBackgroundResource(R.drawable.cell_shape);


                row.addView(et1);
                row.addView(tv1);

                if (i == 0 && j == 1)  // row of operation
                {
                    tv1.setText(Operation);
                    et1.setText(Operation);
                    et1.setEnabled(false);
                }
                else if (Mode.equals("Easy"))
                {
                    if (j == 1 || i == 0)   //fill the first row and the first column
                    {
                        //This gives a random integer between 50 (inclusive) and 5 (exclusive), one of 5,6,...,49
                        Random r = new Random();
                        int random = r.nextInt(50 - 5) + 5;
                        et1.setText(Integer.toString(random));
                        tv1.setText(Integer.toString(random));
                        et1.setEnabled(false);
                    }
                }
                else if (Mode.equals("Hard"))
                {
                    if (j == 1 || i == 0)   //fill the first row and the first column
                    {
                        //This gives a random integer between 65 (inclusive) and 400 (exclusive), one of 65,66,...,399
                        Random r = new Random();
                        int random = r.nextInt(400 - 65) + 65;
                        et1.setText(Integer.toString(random));
                        tv1.setText(Integer.toString(random));
                    }
                }
            }
            ll.addView(row,i);
        }

        //fill center cells
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (j != 1 && i != 0)   // first row and first column are filled
                {
                    String id = "10" + Integer.toString(j);
                    TextView t1 = (TextView) findViewById(Integer.parseInt(id));
                    String id2 = "1" + Integer.toString(i) + "1";
                    TextView t2 = (TextView) findViewById(Integer.parseInt(id2));

                    int res = 0;
                    if (Operation.equals("+"))
                        res = Integer.parseInt(t1.getText().toString()) + Integer.parseInt(t2.getText().toString());
                    else if (Operation.equals("-"))
                        res = Integer.parseInt(t1.getText().toString()) - Integer.parseInt(t2.getText().toString());
                    else if (Operation.equals("*"))
                        res = Integer.parseInt(t1.getText().toString()) * Integer.parseInt(t2.getText().toString());

                    id = "1" + Integer.toString(i) + Integer.toString(j);
                    TextView tres = (TextView) findViewById(Integer.parseInt(id));
                    tres.setText(Integer.toString(res));
                }

                if (Mode.equals("Hard"))
                {
                    String idEdittext = "0" + Integer.toString(i) + Integer.toString(j);
                    EditText et1 = (EditText) findViewById(Integer.parseInt(idEdittext));
                    if (j == 1)
                    {
                        if (((j + i) % 2) == 0)  // if j+i is a digit
                            et1.setEnabled(false);
                        else
                            et1.setText("");
                    }
                    else if(i == 0)
                    {
                        String index = Integer.toString(i) + Integer.toString(j);
                        if ((Integer.parseInt(index) % 2) != 0)	 // if ij is a digit
                            et1.setEnabled(false);
                        else
                            et1.setText("");
                    }

                    if (i == (j-1))
                    {
                        String idTextview = "1" + Integer.toString(i) + Integer.toString(j);
                        TextView t1 = (TextView) findViewById(Integer.parseInt(idTextview));
                        et1.setEnabled(false);
                        et1.setText(t1.getText());
                    }
                }
            }
        }
    }

    public void CheckSolutions(int rows, int cols){
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j <= cols; j++) {
                String idTextView = "1" + Integer.toString(i) + Integer.toString(j);
                String idEditText = "0" + Integer.toString(i) + Integer.toString(j);

                TextView tv = (TextView) findViewById(Integer.parseInt(idTextView));
                EditText et = (EditText) findViewById(Integer.parseInt(idEditText));

                boolean enabled = et.isEnabled();

                if (enabled == true)
                {
                    if (!et.getText().toString().trim().equals(""))
                    {
                        if (checkIfNumber(et.getText().toString()) == true)
                        {
                            int tvValue = Integer.parseInt(tv.getText().toString());
                            int etValue = Integer.parseInt(et.getText().toString());
                            if (tvValue == etValue)
                                et.setTextColor(Color.GREEN);
                            else
                                et.setTextColor(Color.RED);
                        }
                        else
                        {
                            et.setTextColor(Color.RED);
                        }
                    }
                }
            }
        }
    }

    public void ShowSolutions(int rows, int cols, int W){
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j <= cols; j++) {
                String idTextView = "1" + Integer.toString(i) + Integer.toString(j);
                String idEditText = "0" + Integer.toString(i) + Integer.toString(j);

                TextView tv = (TextView) findViewById(Integer.parseInt(idTextView));
                EditText et = (EditText) findViewById(Integer.parseInt(idEditText));

                tv.setVisibility(View.VISIBLE);
                tv.setWidth(W);

                et.setVisibility(View.INVISIBLE);
                et.setWidth(0);

                Button nextBTN = (Button) findViewById(R.id.BTNNext);
                nextBTN.setVisibility(View.INVISIBLE);

                Button BTNRes = (Button) findViewById(R.id.BTNRes);
                BTNRes.setVisibility(View.INVISIBLE);
            }
        }
    }

    public static boolean checkIfNumber(String in) {
        try {
            Integer.parseInt(in);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
