package io.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import io.model.Game;
import io.view.IOFrame;

/**
 * Controller class for the IO project.
 * @author Jacob Smith
 * @version 1.0 13/12/2013 Added start method.
 */

public class IOController
{
	/**
	 * Reference to the GUI Frame for the project.
	 */
	private IOFrame appFrame;
	/**
	 * this initializes project games as an arrayList of type game.
	 */
	private ArrayList<Game> projectGames;
	/**
	 * this sets the projectGames as a new arrayList of the type Game
	 */
	public IOController()
	{
		projectGames = new ArrayList<Game>();
	}

	/**
	 * this initializes projectGames as an arrayList of time Game
	 * @return
	 */
	public ArrayList<Game> getProjectGames()
	{
		return projectGames;
	}

	/**
	 * start method for the controller and the runner
	 */
	public void start()
	{
		appFrame = new IOFrame(this);
	}

	public Game readGameInformation()
	{
		String fileName = "save file.txt"; // Without a path it will look to the
											// directory of the project!
		File currentSaveFile = new File(fileName);
		Scanner fileReader;
		Game currentGame = null;
		int gameRanking = 0;
		String gameTitle = "";
		ArrayList<String> gameRules = new ArrayList<String>();

		/**
		 * Major Scanner methods!!!! 
		 * .next() - the next string separated by whitespace so if the file had "twas brilliant and the slithey....." .next() would return twas 
		 * .nextLine() - returns an entire line of text
		 * .nextInt() - returns the next integer value 
		 * .nextBoolean() - returns the next boolean value 
		 * .nextDouble() - the next Double value
		 */

		try
		{
			fileReader = new Scanner(currentSaveFile);
			gameTitle = fileReader.nextLine();
			gameRanking = fileReader.nextInt();

			while (fileReader.hasNext())
			{
				gameRules.add(fileReader.nextLine());
			}

			currentGame = new Game(gameRules, gameRanking, gameTitle);
			fileReader.close();
		}
		catch (FileNotFoundException currentFileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, currentFileDoesNotExist.getMessage());
		}
		return currentGame;
	}

	/**
	 * this akes the input and reads and then puts in the save file.txt area.
	 * @return fileContents
	 */
	private String readAllGameInformation()
	{
		String fileContents = "";
		String fileName = "save file.txt";
		File currentSaveFile= new File(fileName); 
		Scanner fileReader; 
		try 
		{
			fileReader = new Scanner(currentSaveFile);
			while(fileReader.hasNext())
			{
				fileContents += fileReader.nextLine();
			}
			fileReader.close();
		}
		catch(FileNotFoundException fileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, fileDoesNotExist.getMessage());
		}
		return fileContents;
	}
	
	/**
	 * 
	 * @param currentInfo
	 */
	private void convertTextToGames(String currentInfo)
	{
		String [] gameChunks = currentInfo.split(";");
		for(String currentBlock : gameChunks)
		{
			int currentIndex = currentBlock.indexOf("\n:");
			String title = currentBlock.substring(0, currentIndex);
			int nextIndex = currentBlock.indexOf("\n", currentIndex);
			String ranking = currentBlock.substring(currentIndex+1, nextIndex);
			String rules = currentBlock.substring(nextIndex+1);
			Game currentGame = makeGameFromInput(title, ranking, rules);
			projectGames.add(currentGame);
		}
	}
	
	/**
	 * this takes a game that is saved in the save text.file and picks a random one.
	 * @return currentGame
	 */
	public Game pickRandomGameFromSaveFile()
	{
		Game currentGame = null;
		String allInfo = readAllGameInformation();
		convertTextToGames(allInfo);
		int randomIndex = (int) (Math.random() * (double) projectGames.size());
		currentGame = projectGames.get(randomIndex);

		return currentGame;
	}

	/**
	 * this takes the gameTitle, gameRanking and the gameRules and makes a game from it by reading the strings.
	 * @param gameTitle
	 * @param gameRanking
	 * @param gameRules
	 * @return currentGame
	 */
	public Game makeGameFromInput(String gameTitle, String gameRanking, String gameRules)
	{
		Game currentGame = new Game();
		currentGame.setGameTitle(gameTitle);
		
		if (checkNumberFormat(gameRanking))
		{
			currentGame.setFunRanking(Integer.parseInt(gameRanking));
		}
		else
		{
			return null;
		}
		
		String[] temp = gameRules.split("\n");
		ArrayList<String> tempRules = new ArrayList<String>();
		
		for (String tempWord : temp)
		{
			tempRules.add(tempWord);
		}
		
		currentGame.setGameRules(tempRules);
		
		return currentGame;
		}
	
	/**
	 * this checks if the input in the gameRanking label is an integer, providing you with a messge to change the input to an integer if it isn't.
	 * @param toBeParsed
	 * @return isNumber
	 */
	private boolean checkNumberFormat(String toBeParsed)
	{
		boolean isNumber = false;
		
		try
		{
			int valid = Integer.parseInt(toBeParsed);
			isNumber = true;
		}
		catch (NumberFormatException error)
		{
			JOptionPane.showMessageDialog(appFrame,  "Please try again with an actual number.");
		}
		
		return isNumber;
	}
	/**
	 * this creates an ability for the program to save text files
	 * @param currentGame
	 */
	public void saveGameInformation(Game currentGame)
	{
		PrintWriter gameWriter;
		String saveFile = "save file.txt";
		projectGames.add(currentGame);
		
		try
		{
			gameWriter = new PrintWriter(saveFile); // Creates the save File.

			gameWriter.println(currentGame.getGameTitle());
			gameWriter.println(currentGame.getFunRanking());

			for (int count = 0; count < currentGame.getGameRules().size(); count++)
			{
				gameWriter.println(currentGame.getGameRules().get(count));
			}

			gameWriter.println(";"); // Used to delineate each individual game in the save file

			gameWriter.close();
		}

		catch (FileNotFoundException noFileExists)
		{
			JOptionPane.showMessageDialog(appFrame, "Could not create the save file");
			JOptionPane.showMessageDialog(appFrame, noFileExists.getMessage());
		}
	}
}
