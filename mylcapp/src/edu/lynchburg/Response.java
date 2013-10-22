package edu.lynchburg;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.*;

//This class holds all of the data expected from a server response.

/*
<response>
<action>action</action>
<sessionid>sessionid</sessionid>
<result>result</result>
<resulttext>resulttext</result>
<data>data</data>
</response>
*/

/*
 * Now annotated with SimpleXML for deserialization
 */
@Root
public class Response {
		
	@Element
	private String action;	
	@Element (required= false)
	private String sessionId;	
	@Element
	private int result;	
	@Element
	private String resultText;	
	@ElementList
	private List<String> data;	
	
	public String getAction(){
		return action;
	}	
	public String getSessionId(){
		return sessionId;
	}
	public int getResult(){
		return result;
	}
	public String getResultText(){
		return resultText;
	}
	public List<String> getData(){
		return data;
	}
	
	Response(String action, int id, String sessionId, int result, String resultText, List<String> data)
	{
		this.action = action;		
		this.sessionId = sessionId;
		this.result = result;
		this.resultText = resultText;
		this.data = new ArrayList<String>(data);
	}
	Response(){
		data = new ArrayList<String>();
	}
	
	//tag names (if I use SimpleXML, I don't need these)
	public static final String RESPONSE = "response";
	public static final String ID = "id";
	public static final String ACTION = "action";
	public static final String SESSIONID = "sessionid";
	public static final String RESULT = "result";
	public static final String RESULTTEXT = "resulttext";
	public static final String DATA = "data";
	
}
