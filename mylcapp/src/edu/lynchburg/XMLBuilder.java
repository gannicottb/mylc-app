package edu.lynchburg;
import java.io.StringWriter;


import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

/**
 * Just like the XMLParser, we need something to craft requests
 * It should take in some parameters and then return an XML object that's ready to go 
 */
public class XMLBuilder {

	//tag names
	/*
	
	public static final String ID = "id";
	public static final String ACTION = "action";
	public static final String SESSIONID = "sessionid";	
	*/
	/**
	 * This is a very basic builder that crafts an XML object in this format:
	 * <request id = id>
	 * <action>action</action>
	 * <sessionid>sessionid</sessionid>
	 * </request>
	 * @param	action	the action code for this request (I want this to be an Action eventually)
	 * @param	sessionid	the sessionid for this request (This will become 'token', I suspect)
	 * @return finished XML object as a String object
	 */
	
	public static String build(String action, long sessionid)
	{
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		//This is a filler - need to find some other way to handle id
		String id = "0";
		try{
			serializer.setOutput(writer);
			serializer.startDocument("utf-8", true);
			serializer.startTag("", Request.REQUEST);
			serializer.attribute("", Request.ID , id);
			serializer.startTag("", Request.ACTION);
			serializer.text(action);
			serializer.endTag("", Request.ACTION);
			serializer.startTag("", Request.SESSIONID);
			serializer.text(Long.toString(sessionid));
			serializer.endTag("", Request.SESSIONID);
			//here is where I could add a data section
			//maybe .. if(there is data){put data in according to tags() and data()
			serializer.endTag("", Request.REQUEST);	
			serializer.endDocument();
			return writer.toString();			
		}
		
		catch(Exception e){
			throw new RuntimeException(e);			
		}
	}	
}
