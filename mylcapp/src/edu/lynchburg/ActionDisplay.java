package edu.lynchburg;

/**
 * This is one of two interfaces used for the two helper classes associated with each Action
 * Every Action (login, meals, money, bio, etc.) has an ActionData and ActionDisplay class.
 * Those classes implement the appropriate interface (ActionData or ActionDisplay).
 * The interfaces contain the necessary functions, and each class implements them differently.
 * Additionally, the Display class will likely extend ViewGroup, not sure if that will cause a problem
 * @author Brandon Gannicott
 *
 */
public interface ActionDisplay {
/**
 * The Display driver/class for an Action will be a ViewGroup, and will use the Data driver/class
 * 
 */
	/**
	 * This may be unnecessary, but I want a way to link the Data and Display classes
	 * @param d the ActionData class with all of the data
	 */
	public void setData(ActionData d);
	/**
	 * This will use ViewGroup functions to show a simple version of the data, like a preview
	 */
	public void showGlance();
	/**
	 * This will show the fully detailed, expanded version of the data
	 */
	public void showDetail();
}
