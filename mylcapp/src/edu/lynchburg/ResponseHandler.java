package edu.lynchburg;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class ResponseHandler {	
	
	/**
	 * This class should get the <action> field from the response, and use that value
	 * to instantiate an instance of the proper data driver for that action.
	 * e.g: "Class dataDriver = Class.forName(message.action+"_data")
	 * the dataDriver will then return the data in the proper format to
	 * the displayDriver for that action, which then goes to the interface
	 * That's where it gets hazy, unfortunately. 8/1/12
	 */
	
	public static final String STRING = "string";
	public static final String DATA = "data";
	public static final String PACKAGENAME = "edu.lynchburg";
	
	
	/**
	 * Determines proper data driver class to send the action data to
	 * @param	response	the message received from the server 
	 * @param	ctx			the application context
	 */
	/*
	public void handle(InputStream response, Context ctx){
		//what sort of action are we working with?
		//I already have a Response object... why not use that here?
		Response fromServer = TestParser.parse(response);
		
		//does that action exist? First we attempt to retrieve R.string id	
		int actionId = ctx.getResources().getIdentifier(fromServer.getAction(), STRING, PACKAGENAME);
		String confirmedAction = ctx.getString(actionId);
		//now we use context.getString() to compare the action name on file to the one we have
		if(fromServer.getAction().equalsIgnoreCase(confirmedAction))
		try{	
			//dynamically initialize proper data class object
			Class dataDriver = Class.forName(PACKAGENAME + "." + confirmedAction + DATA);
			ActionData a = (ActionData)dataDriver.newInstance();
			//load the Response from server into the object
			a.loadXML(fromServer);
			
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch(InstantiationException e){
			e.printStackTrace();
		}
		//this is a red flag
		catch (IllegalAccessException e) {			
			e.printStackTrace();
		}
		
		
	}
	*/
	public static Message handle(InputStream response, Context ctx)	
	{
		//First - deserialize response into a Response object
		Message msg = new Message();
		Serializer serializer = new Persister(); 
		try{
			//deserialize!
			msg = serializer.read(Message.class, response);			
		}catch(Exception e){
	   		e.printStackTrace();	   		
	   	}
		
		return msg;
		
	}
	
	
	
}
