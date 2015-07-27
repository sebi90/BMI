package dma.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity {

    public static final String Message = "Message";
    private static final int REQUEST_CODE = 1;
    private double weight, height;
    private TextView textWeight, textHeight, textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textWeight= (TextView) findViewById(R.id.textViewWeight);
        textHeight = (TextView) findViewById(R.id.textViewHeight);
        textView = (TextView) findViewById(R.id.textViewResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View view)
    {
        Intent intent = new Intent(this, InputActivity.class);
        switch (view.getId())
        {
            case R.id.buttonHeight:
                intent.putExtra(Message, true);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.buttonWeight:
                intent.putExtra(Message, false);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE)
        {
            Boolean weight_height = intent.getBooleanExtra(InputActivity.WEIGHT_HEIGTH, true);
            String result = intent.getStringExtra(InputActivity.RESULT);
            if(weight_height)
            {

                if(result.length() != 0) {
                    textHeight.setText(result + " cm");
                    height = Double.parseDouble(result.toString());
                }
                else
                {
                    textHeight.setText("ungültige Eingabe");
                }
            }
            else
            {

                if(result.length() != 0) {
                    textWeight.setText(result + " kg");
                    weight = Double.parseDouble(result.toString());
                }
                else
                {
                    textWeight.setText("ungültige Eingabe");
                }
            }

        }
            double result = (weight / (height * height)) * 10000;
            textView.setText("Der BMI-Wert beträgt: " + result);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("DISPLAY_TEXT_WEIGHT", textWeight.getText().toString());
        outState.putString("DISPLAY_TEXT_HEIGHT", textHeight.getText().toString());
        outState.putString("DISPLAY_TEXT", textView.getText().toString());

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textWeight.setText(savedInstanceState.getString("DISPLAY_TEXT_WEIGHT"));
        textHeight.setText(savedInstanceState.getString("DISPLAY_TEXT_HEIGHT"));
        textView.setText(savedInstanceState.getString("DISPLAY_TEXT"));
    }
}
