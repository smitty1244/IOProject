package io.model;

import java.util.ArrayList;

public class Game
{
	/**
	 * this sets gameRules as an ArrayList of Strings
	 */
	private ArrayList<String> gameRules;
	
	/**
	 *this sets funRanking as an int 
	 */
	private int funRanking;
	
	/**
	 * this sets the gameTitle as a string
	 */
	private String gameTitle;
	
	/**
	 * this constructs the game rules, funRanking, and the gameTitle
	 */
	public Game()
	{
		gameRules = new ArrayList<String>();
		funRanking = 0;
		gameTitle = "";
	}

	/**
	 * this also constructs the gameRules, funRankming and the gameTitle by use overloading
	 */
	public Game(ArrayList<String> gameRules, int funRanking, String gameTitle)
	{
		this.gameRules = gameRules;
		this.funRanking = funRanking;
		this.gameTitle = gameTitle;
	}

	/**
	 * 
	 */
	public void play()
	{
		
	}

	/**
	 * this returns the ArrayList game rules
	 */
	public ArrayList<String> getGameRules()
	{
		return gameRules;
	}

	/**
	 * this returns the int funRanking
	 */
	public int getFunRanking()
	{
		return funRanking;
	}

	/**
	 * this gets the string of gameTitle
	 */
	public String getGameTitle()
	{
		return gameTitle;
	}

	/**
	 * this sets rules as an arraylist of rules
	 */
	public void setGameRules(ArrayList<String> rules)
	{
		gameRules = rules;
	}

	/**
	 * this sets funRanking as funranking
	 */
	public void setFunRanking(int funRanking)
	{
		funRanking = getFunRanking();
	}

	/**
	 * this sets the gmae title as a title
	 */
	public void setGameTitle(String title)
	{
		gameTitle = title;
	}

}
