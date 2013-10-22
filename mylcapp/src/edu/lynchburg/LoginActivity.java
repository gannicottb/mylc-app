package edu.lynchburg;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.*;
import org.simpleframework.xml.core.Persister;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends Activity implements OnClickListener{
	CheckBox rememberMe;
	Button loginButton;
	EditText user;
	EditText pass;
	EditText pin;
	//Bundle bundle;
	 SharedPreferences settings;
	public static final String USERNAME_CACHE = "user";
	public static final String PASSWORD_CACHE = "pass";
	public static final String PIN_CACHE = "pin";
	public static final String REMEMBER = "remember";
	public static final String PREFS_NAME = "prefs";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
             
        	
        //set up the View objects
        rememberMe = (CheckBox)findViewById(R.id.remember);
        loginButton = (Button)findViewById(R.id.login_btn);
        
        user = (EditText)findViewById(R.id.userField);
        pass = (EditText)findViewById(R.id.passField);
        pin = (EditText)findViewById(R.id.pinField);
        
        //bundle = new Bundle();
        
        //do we fill in the fields with remembered data?
        settings = getSharedPreferences(PREFS_NAME, 0);
        if(settings.getBoolean(REMEMBER, false))
        {
        	//TODO: Decrypt the hashed value to display it here.
        	user.setText(settings.getString(USERNAME_CACHE, ""));
        	pass.setText(settings.getString(PASSWORD_CACHE, ""));
        	pin.setText(settings.getString(PIN_CACHE, ""));
        }
        
        rememberMe.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View arg0) {
		settings = getSharedPreferences(PREFS_NAME, 0);
		//Toast.makeText(this, "click!", Toast.LENGTH_SHORT).show();
		switch(arg0.getId())
		{
		case R.id.remember:
			//Set "remember" to the value of the checkbox					
			settings.edit().putBoolean("remember", rememberMe.isChecked());			
			settings.edit().commit();
			break;
		case R.id.login_btn:				
			//Build temporary strings with info from login screen
			//TODO: Hash and salt info according to LC server's scheme
									
			String _user = user.getText().toString();			
			String _pass = pass.getText().toString();
			String _pin = pin.getText().toString();
			
			//if "remember me" is true in the preferences
			if(settings.getBoolean("remember", false)){
				//put the info into the preferences (it's hashed and salted already)				
				settings.edit().putString(USERNAME_CACHE, _user);
				settings.edit().putString(PASSWORD_CACHE, _pass);
				settings.edit().putString(PIN_CACHE, _pin);				
			}
			//send the info on to the LC server - use the hashed and salted values generated above.
			List<String> loginInfo = new ArrayList<String>();			
			loginInfo.add(_user);
			loginInfo.add(_pass);
			loginInfo.add(_pin);
			
			/* UNDER CONSTRUCTION
			
			ConnectionLayer cl = new ConnectionLayer();
			InputStream attempt = cl.getResponse(new Request("login", 0, loginInfo));
			???? = ResponseHandler.handle(attempt, this.getBaseContext());
			
			Put the whole response in the bundle?
			Just the sessionId?
			
			//The user may put their information in improperly and need to try again	
				
			UNDER CONSTRUCTION */
			
			//Now start up the HomeActivity
			Intent mIntent = new Intent(this, HomeActivity.class);
			startActivity(mIntent);
			break;
		
		default: break;		
		}
		
		
	}
	private void handleLogin(){
		//Build temporary strings with info from login screen
		//TODO: Hash and salt info according to LC server's scheme
		
		String _user = user.getText().toString();			
		String _pass = pass.getText().toString();
		String _pin = pin.getText().toString();
		
		//if "remember me" is true in the preferences
		if(settings.getBoolean("remember", false)){
			//put the info into the preferences (it's hashed and salted already)				
			settings.edit().putString(USERNAME_CACHE, _user);
			settings.edit().putString(PASSWORD_CACHE, _pass);
			settings.edit().putString(PIN_CACHE, _pin);				
		}
		//send the info on to the LC server - use the hashed and salted values generated above.
		List<String> loginInfo = new ArrayList<String>();			
		loginInfo.add(_user);
		loginInfo.add(_pass);
		loginInfo.add(_pin);
		
		ConnectionLayer cl = new ConnectionLayer();
		//Request login = new Request("login", 0, loginInfo);
		Message login = new Message(); 
		login.setAction("login");
		login.setSessionId("0");
		login.getData().addAll(loginInfo);
		
		InputStream attempt = cl.getResponse(login);
		if(attempt != null){
			//I really ought to have a ResponseHandler
			//Actually, I DO have one, it just needs to be updated.
			Message loginResponse = ResponseHandler.handle(attempt, this.getBaseContext());
			//This response will have a sessionId. This is an important thing to have, and I'll need it
			//for all messages to the server, until it times out.
			//As far as what to do with it now, ....
			/*if(loginResponse.result != 0)
			  	then I guess I want to return the sessionId
			*/
		}
		
		
	}

}
