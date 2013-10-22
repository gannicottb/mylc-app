package edu.lynchburg;

import java.util.ArrayList;


public class Login extends Action {
	private String _user;
	private String _pass;
	private String _pin;
	
	Login(String user, String pass, String pin){
		name = LOGIN;		
		_user = user;
		_pass = pass;
		_pin = pin;
	}
	
	public ArrayList<String> tags()
	{
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("user");
		tags.add("pass");
		tags.add("pin");
		return tags;
	}
	
	public ArrayList<String> data()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add(_user);
		data.add(_pass);
		data.add(_pin);		
		return data;
	}
}
