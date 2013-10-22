package edu.lynchburg;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class TestParser {
	
	/**
	 * This is a very basic parser that parses an XML object into a Response object, according to this schema:
	 * <response id = id>
	 * <action>action</action>
	 * <sessionid>sessionid</sessionid>
	 * <result>result</result>
	 * <data>data</data>
	 * </response>
	 * @param	istream	the InputStream received from the server containing the XML message
	 * @return finished Response object with data from the XML message
	 */
	/*
	 public static Response parse(InputStream istream){
		 //create a blank Response object
		 Response msg = null;
		 
		 try{
			// get a new XmlPullParser object from Factory			
			XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
			// set input source			 
			parser.setInput(istream, null);		
			// get event type		
			int eventType = parser.getEventType();			
			//process tags until END_DOCUMENT reached
			while(eventType != XmlPullParser.END_DOCUMENT){
				switch(eventType){
				//At start of document, so we'll go ahead and initialize msg.
				case XmlPullParser.START_DOCUMENT:
					msg = new Response();
					break;
				//we're at the start of a tag.
				case XmlPullParser.START_TAG:
					//get tag name (important!)
					String tagName = parser.getName();
					//since <title> has an attribute, we'll handle that here
					if(tagName.equalsIgnoreCase(Response.RESPONSE)){
						//set test.mId with getAttributeValue(passing in attribute name) 
						msg.responseId = Integer.parseInt(parser.getAttributeValue(null, Response.ID));
					}
					//now we just check all the other tags
					else if (tagName.equalsIgnoreCase(Response.ACTION)){
						msg.action = parser.nextText();
					}
					else if (tagName.equalsIgnoreCase(Response.SESSIONID)){
						msg.sessionId = Long.parseLong(parser.nextText());
					}					
					else if (tagName.equalsIgnoreCase(Response.RESULT)){
						msg.result = Integer.parseInt(parser.nextText());
					}
					else if (tagName.equalsIgnoreCase(Response.RESULTTEXT)){
						msg.resulttext = parser.nextText();
					}	
					else if (tagName.equalsIgnoreCase(Response.DATA)){
						//there could be any number of string elements here
						msg.data = parseDataField(parser);
						
					}
					//handle data from the server
					break;
					//done with switch
				}
				eventType = parser.next();
				
			}
		//we have to catch exceptions		
		 }catch(XmlPullParserException e){
			 msg = null;
		 }catch(IOException e){
			 msg = null;
		 }
		 
		 //return our test object
		 return msg;
		 
	 }
	 private static ArrayList<String> parseDataField(XmlPullParser xpp)
	 throws IOException, XmlPullParserException
	 {		 
		 ArrayList<String> result = new ArrayList<String>();
		 int eventType = xpp.nextTag();
		 while(eventType != XmlPullParser.END_TAG)
		 {			 
			 if(eventType == XmlPullParser.START_TAG)
			 {
				 result.add(xpp.nextText());//throws XmlPullParserException when it tries to get the second field
			 }			 
		 }//this should grab all elements in the data field
		 return result;
	 }
	 */
}

