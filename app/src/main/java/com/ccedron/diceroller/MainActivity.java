package com.ccedron.diceroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainApp";
    private static final String ROLL_HISTORY_FILE = "rollHistory.txt";
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.LightTheme);
        setContentView(R.layout.activity_main);
        updateTextBox();
    }

    //////////////////////////////////////////Button Functions//////////////////////////////////////
    public void rollCoin(View notUsed) {
        RollResult result = rollGeneric(2);
        switch (result.rollNumeric){
            case 1:
                result.rollText = "Heads";
                break;
            case 2:
                result.rollText = "Tails";
                break;
            default:
                result.rollText = "Roll Error";
                break;
        }

        if(result.rollNumeric > 0) {
            writeRoll(result);
        }
    }
    public void roll4(View notUsed) {
        RollResult result = rollGeneric(4);

        if(result.rollNumeric > 0) {
            writeRoll(result);
        }
    }
    public void roll6(View notUsed) {
        RollResult result = rollGeneric(6);

        if(result.rollNumeric > 0) {
            writeRoll(result);
        }
    }
    public void roll8(View notUsed) {
        RollResult result = rollGeneric(8);

        if(result.rollNumeric > 0) {
            writeRoll(result);
        }
    }
    public void roll10(View notUsed) {
        RollResult result = rollGeneric(10);

        if(result.rollNumeric > 0) {
            writeRoll(result);
        }
    }
    public void roll12(View notUsed) {
        RollResult result = rollGeneric(12);

        if(result.rollNumeric > 0) {
            writeRoll(result);
        }
    }
    public void roll20(View notUsed) {
        RollResult result = rollGeneric(20);

        if(result.rollNumeric > 0) {
            writeRoll(result);
        }
    }


    public void writeText(View notUsed){
        writeToFile("helloWorld\n");
    }

    public void clearFile(View notUsed){
        deleteFile();
        updateTextBox();
    }

    public void setName(View notUsed){
        EditText editText = (EditText)findViewById(R.id.nameField);
        String newUserName = editText.getText().toString();

        if(userName != newUserName && newUserName.length() > 0){
            userName = newUserName;
            Toast.makeText(this, "Name Set", Toast.LENGTH_SHORT).show();
            Log.i(TAG, String.format("Name set to: %s", userName));
        }else{
            Toast.makeText(this, "Please Enter A New Name", Toast.LENGTH_SHORT).show();
        }

        editText.onEditorAction(EditorInfo.IME_ACTION_DONE);

    }
    ///////////////////////////////////////File Handlers////////////////////////////////////////////
    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(ROLL_HISTORY_FILE, Context.MODE_APPEND));
            outputStreamWriter.append(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    private void deleteFile(){
        deleteFile(ROLL_HISTORY_FILE);
    }
    private String readFromFile() {
        String ret = "";

        try {
            InputStream inputStream = openFileInput(ROLL_HISTORY_FILE);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    ///////////////////////////////////////////////MISC/////////////////////////////////////////////
    private void writeRoll(RollResult roll){
        String newLine = String.format("%s: %s\n",roll.name, roll.rollText);

        Log.i(TAG, newLine);

        writeToFile(newLine);
        updateTextBox();
    }

    public RollResult rollGeneric(int maxRoll){
        int roll = rollRandom(maxRoll);
        String stringRoll = getRollText(roll);

        if(userName != null){
            return new RollResult(roll, stringRoll, userName);
        }
        else{
            Toast.makeText(this, "Please Set Your Name First", Toast.LENGTH_SHORT).show();
            return new RollResult(-1, "NaN", "NoName");
        }
    }

    public String getRollText(int roll) {
        switch (roll) {
            case 1:
                return "1 - One";

            case 2:
                return "2 - Two";

            case 3:
                return "3 - Three";

            case 4:
                return "4 - Four";

            case 5:
                return "5 - Five";

            case 6:
                return "6 - Six";

            case 7:
                return "7 - Seven";

            case 8:
                return "8 - Eight";

            case 9:
                return "9 - Nine";

            case 10:
                return "10 - Ten";

            case 11:
                return "11 - Eleven";

            case 12:
                return "12 - Twelve";

            case 13:
                return "13 - Thirteen";

            case 14:
                return "14 - Fourteen";

            case 15:
                return "15 - Fifteen";

            case 16:
                return "16 - Sixteen";

            case 17:
                return "17 - Seventeen";

            case 18:
                return "18 - Eighteen";

            case 19:
                return "19 - Nineteen";

            case 20:
                return "20 - Twenty";

            default:
                return "RollError";
        }
    }
    public int rollRandom(int maxRoll) {
        //make the object that will do the random rolling
        Random roller = new Random();
        //generate a new random number, add one to move the min from 0 to 1
        int randomRoll = roller.nextInt(maxRoll) + 1;

        //return the generated value
        return randomRoll;
    }
    private void updateTextBox() {
        NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.rollOutputScroller);
        TextView textView = (TextView)findViewById(R.id.rollOutput);

        String val = readFromFile();
        Log.i(TAG, val);

        textView.setText(val);
        scrollView.scrollTo(0, textView.getBottom());
    }

}
