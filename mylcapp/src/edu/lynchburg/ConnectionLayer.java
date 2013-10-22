package edu.lynchburg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class ConnectionLayer {
	
	public static final String serverURL = "https://apps.lynchburg.edu/campus/system/api.asp";
	public static final String url = "http://10.0.2.2:8080/StudentDataService";
	public static final String androidTestURL = "http://www.android.com/";
	
		
	/**
	 * Grabs the message from the server with an HttpURLConnection
	 * @param Request object with all required information for a transaction
	 * @return InputStream message from server
	 */	
	//HTTPGET and POSt are different. Two methods? Overload? If statement in one method?
	//maybe... use GET if data is blank, otherwise use POST?
	//This is an implementation that sends GET if the request.data is empty, and POST if something's there
	public InputStream getResponse(Message appMsg) 
	{	
		InputStream result = null;
		//set timeout values
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 3000);
		HttpConnectionParams.setSoTimeout(params, 5000);
		//initialize client with above timeouts
		HttpClient client = new DefaultHttpClient(params);
		
		String extra ="/serve";		
		//POST or GET?		
			//we're always sending data, as well. POST
			
			HttpPost post = new HttpPost(url+extra);
			
			//Prepare the data
			//<1> Serialize the Message into an OutputStream (XML)
			Serializer serializer = new Persister();	        	        
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        try{
	        	serializer.write(appMsg, out);
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	        //<2> Create an HttpEntity with the outputstream
			HttpEntity send = new ByteArrayEntity(out.toByteArray());
			
			//<3> Add the HttpEntity to the HttpPost			
			post.setEntity(send);
			post.setHeader("Content-type", "application/xml");
			//<4> Send the HttpPost to the server, get inputStream
			HttpResponse fromServer = null;			
			try{
				fromServer = client.execute(post);
				result = fromServer.getEntity().getContent();
			}catch(ClientProtocolException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
			
			//hopefully there's an inputStream in result
			
		
		return result;
		
	}  
	/*
	public InputStream getResponse(Request request) 
	{	
		InputStream result = null;
		//set timeout values
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 3000);
		HttpConnectionParams.setSoTimeout(params, 5000);
		//initialize client with above timeouts
		HttpClient client = new DefaultHttpClient(params);
		
		String extra ="/"+request.getAction() +"/";		
		//PUT or GET?
		if(request.getData().isEmpty())
		{
			//then we're just getting data. GET.
			try{	
				
				//FOR NOW, send the individualid(as set in HomeActivity debug section)
					//(the individualId would be discovered server side with the use of the sessionID, but that isn't implemented yet)			
				HttpGet get = new HttpGet(url+extra+request.getSessionId());				
	        	HttpResponse response = client.execute(get); 
	        	HttpEntity entity = response.getEntity();       	
	        
	        	result = entity.getContent();
	        
	        }catch(ClientProtocolException e){
	        	e.printStackTrace();
	        }
	        catch(IOException e){
	        	e.printStackTrace();
	        }
			catch(IllegalStateException e){
				e.printStackTrace();
			}
			
		}
		else
		{
			//we're sending data, as well. POST
			
			//HttpPost post = new HttpPost(url+extra);
			
			//HttpEntity send = new UrlEncodedFormEntity(request.getData());
			
			//post.setEntity(entity);
			//etc., etc.
			//result = entity.getContent();
		}
		return result;
		
	}*/  
	
	
	 /* public InputStream getResponse(Request r) {		
		HttpClient client = new DefaultHttpClient();     
	    
		String extra ="/"+r.getAction() +"/";
	        
		try{	
			//eventually send XML to server (server can't parse XML yet)
			//HttpGet get = new HttpGet(url+extra+URLEncoder.encode(r.xml, "UTF-8"));
			//FOR NOW, send the individualid (again, this would be discovered by the server, but that isn't implemented yet)
			HttpGet get = new HttpGet(url+extra+r.getSessionId());
			
        	HttpResponse response = client.execute(get); 
        	HttpEntity entity = response.getEntity();        	
        	return entity.getContent();
        
        }catch(ClientProtocolException e){
        	e.printStackTrace();
        }
        catch(IOException e){
        	e.printStackTrace();
        }
		catch(IllegalStateException e){
			e.printStackTrace();
		}
		return null;
		
	}  */
	 
	 public String getResponseAsString(Request r) {		
			HttpClient client = new DefaultHttpClient();     
		    String fromServer = "";
		    String extra ="/"+r.getAction() +"/";
		        
			try{			
				//HttpGet get = new HttpGet(url+extra+URLEncoder.encode(r.xml, "UTF-8"));
				//FOR NOW, send the individualid (again, this would be discovered by the server, but that isn't implemented yet)
				HttpGet get = new HttpGet(url+extra+r.getSessionId());
	        	HttpResponse response = client.execute(get); //http://10.0.2.2:8080/StudentDataService/bio/770005012
	        	HttpEntity entity = response.getEntity(); 	        	
	        	
	        	//read the InputStream and deliver as string
	        	if(entity.getContentLength()!=0){
	        		//InputStreamReader reads the entity's InputStream
	        		InputStreamReader reader = new InputStreamReader(entity.getContent());
	        		//The InputStreamReader can read its stream to a char[] buffer
	        		char[] buffer = new char[(int)entity.getContentLength()];
	        		reader.read(buffer);        		
	        		reader.close();
	        		//initialize fromServer with the buffer
	        		fromServer = new String(buffer);
	        	}  
	        
	        }catch(ClientProtocolException e){
	        	e.printStackTrace();
	        }
	        catch(IOException e){
	        	e.printStackTrace();
	        }
			catch(IllegalStateException e){
				e.printStackTrace();
			}
			return fromServer;
			
		}  
	 
}
