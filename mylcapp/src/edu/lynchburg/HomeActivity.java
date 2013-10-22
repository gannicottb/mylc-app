package edu.lynchburg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;



public class HomeActivity extends Activity implements OnItemClickListener {
	/** Called when the activity is first created. */
	public enum ActionCode {BIO, MEALS, MONEY};
	TextView debug;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        debug = (TextView) findViewById(R.id.home_screen_header);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        
        gridview.setAdapter(new TileAdapter(this));
        gridview.setOnItemClickListener(this);
        
/*
 *         * This is where I test the network functionality of the app
 */
        //I'll have a Request
        //This DEBUG object takes the individualid directly - eventually, the server will take the 'sessionid' or 'token'
        	//and derive the individualid serverside.
        String sessionIdFromLoginResponse = "770005012";
        String desiredAction = "bio";
        //Request r = new Request(desiredAction, sessionIdFromLoginResponse, null);
        Message r = new Message();
        r.setAction(desiredAction);
        r.setSessionId(sessionIdFromLoginResponse);
        
        //I'll have a ConnectionLayer object
        ConnectionLayer cl = new ConnectionLayer();
        //I'll use the ConnectionLayer to get the response, then parse with SimpleXML
        
        String responseString="";
        try{  	
        	
	        InputStream fromServer = cl.getResponse(r);        
	        //InputStream fromServer = getAssets().open("sampleBioResponse.xml");//DEBUG
	        
	        //Serializer serializer = new Persister(); 
	        
	        //Test serializer.write(class, outputstream) - works!
	        /*
	        OutputStream out = new ByteArrayOutputStream();
	        serializer.write(r, out);
	        responseString = out.toString();
	        */
	        
	        
	        //Response response = serializer.read(Response.class, fromServer);//works with sampleBioResponse.xml!
	        
	        Message response = ResponseHandler.handle(fromServer, getBaseContext());
	        
	        
	        responseString = response.getAction()+
									response.getResultText();
	        for (String s : response.getData())
	            responseString += (" "+s+", ");
	         
        }        
        catch(Exception e){
        	responseString = e.getMessage();        	
        }
								
        //Gee, I wonder if that object has anything in it?
        debug.setText(responseString);
        
            //String fromServer = cl.getResponseAsString(r);
        	//Then show it somehow 
        //debug.setText(fromServer);   
/*
 *             
 */
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "" + pos, Toast.LENGTH_SHORT).show();
		switch(pos){
		case 0:
			//do something for this tile
			break;
		case 1:
			//do something for THIS tile
			break;
			default:
		}
	}
}
