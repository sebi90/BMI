package dma.bmi;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class InputActivity extends ActionBarActivity {

    public static final String RESULT = "Result";
    public static final String WEIGHT_HEIGTH = "Weight_Height";
    Boolean message;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Intent intent = getIntent();
        message = intent.getBooleanExtra(MainActivity.Message, true);
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.resultText);

        if(message)
        {
            textView.setText("Bitte Größe (in cm) eingeben:");
        }
        else
        {
            textView.setText("Bitte Gewicht (in kg) eingeben:");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input, menu);
        return true;
    }

    public void clickResult(View view)
    {

        String input = editText.getText().toString();
        Intent intent = getIntent();
        if(message)
        {
            intent.putExtra(WEIGHT_HEIGTH, message);
        }
        else
        {
            intent.putExtra(WEIGHT_HEIGTH, message);
        }
        intent.putExtra(InputActivity.RESULT, input);
        setResult(RESULT_OK, intent);
        finish();

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
        outState.putString("DISPLAY_TEXTVIEW", textView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textView.setText(savedInstanceState.getString("DISPLAY_TEXTVIEW"));
    }
}
