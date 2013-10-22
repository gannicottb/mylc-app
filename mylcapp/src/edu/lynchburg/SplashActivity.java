package edu.lynchburg;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Initially, I planned on this as a coverup so the user didn't have to see the login screen if they had their info saved.
 * But the more I think about it, this could be implemented as a View in a different activity. 
 * @author Brandon
 *
 */

public class SplashActivity extends Activity {
	public static final String PREFS_NAME = "prefs";	
	ImageView splash;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        //check to see if "remember me" was checked at some point
        //if so, then go to the home screen activity, which will use info from preferences
        //if not, then go to login screen and put the info into the preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean remember = settings.getBoolean("remember", false);    
        
        splash = (ImageView)findViewById(R.id.splash_image);
        
        if(remember){ 
        	//TODO: Decrypt info from preferences, put it in the bundle, and pass it on
        	Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class); 
        	startActivityForResult(myIntent, 0);
        }
        else{
        	Intent myIntent = new Intent(getApplicationContext(), LoginActivity.class);
        	startActivityForResult(myIntent, 0);
        }
    }

	

	}


