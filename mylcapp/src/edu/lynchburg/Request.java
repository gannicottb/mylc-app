package edu.lynchburg;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.*;

/*
<request id = id>
<action>action</action>
<sessionid>sessionid</sessionid>
<data>data</data>
</request>
*/


public class Request {

	Request(String action, long sessionId, List<String> data) {
		//initialize other variables
		this.action = action;
		this.sessionId = sessionId;
		if(data==null)
			this.data = new ArrayList<String>();
		else
			this.data = data;		
	}
	
		
	private int id;	
	private String action;
	private long sessionId;
	private List<String> data;
	
	public int getId(){
		return id;
	}
	public String getAction(){
		return action;
	}
	public long getSessionId(){
		return sessionId;
	}
	public List<String> getData(){
		return data;
	}
	
	
	//Constants for XML parsing?
	public static final String REQUEST = "request";
	public static final String ID = "id";
	public static final String ACTION = "action";
	public static final String SESSIONID = "sessionid";
	public static final String DATA = "data";
	
}
