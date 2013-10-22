package edu.lynchburg;

/**
 * The base class for all Actions. Every action has a name and data.
 * I think that @Action_Data is unnecessary because I have this class already.
 * 
 * @author Brandon Gannicott
 *
 */

public abstract class Action {
	//What constitutes an action?
	public String name;
	
	//titles	
	public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String MEALS = "meals";    
    public static final String MONEY = "money";
    public static final String FORMS = "forms";
    public static final String DIRECTORY = "directory";
    public static final String BIO = "bio";
    
    	
}
