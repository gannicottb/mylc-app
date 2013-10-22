package edu.lynchburg;

import java.io.IOException;

import edu.lynchburg.TestParser;
import edu.lynchburg.XMLBuilder;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//This is an app that tests the functionality of the XMLPullParser
//Brandon Gannicott
//6/1/2012

public class XMLParsingActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //create a textview to display results
        TextView tvTest = (TextView)findViewById(R.id.tvTest);     
        /*
        Response msg = null;        
        try{
        	//msg = TestParser.parse(getAssets().open("sampleLoginResponse.xml"));
        	msg = TestParser.parse(getAssets().open("sampleBioResponse.xml"));
        }catch (IOException e){
        	Log.d("XML","onCreate(): parse() failed");
        	return;
        } 
        */ 
      
        /*
        tvTest.setText("SERVER RESPONSE ID: "+msg.responseId+
        		"\nSESSION ID: "+msg.sessionId+
        		"\nRESULT: "+msg.result+
        		"\nRESULT TEXT: "+msg.resulttext);
        
        tvTest.append("\n"+ XMLBuilder.build(msg.resulttext, msg.responseId));
        */         
    }
}