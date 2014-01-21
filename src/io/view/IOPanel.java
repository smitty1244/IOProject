package io.view;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import io.controller.IOController;
import io.model.Game;

/**
 * The panel class for IO
 * 
 * @author jsmi6845
 * @version multiple methods added to create the skeleton of the project
 */
public class IOPanel extends JPanel
{
	/**
	 * This creates the IOConroller as the baseController
	 */
	private IOController baseController;
	/**
	 * this sets the saveButton as a JButton
	 */
	private JButton saveButton;
	/**
	 * this creats the loadButton as a JButton.
	 */
	private JButton loadButton;
	/**
	 * this sets the titleField as a JTextField
	 */
	private JTextField titleField;
	/**
	 * this sets the rankingField as a JTextField
	 */
	private JTextField rankingField;
	/**
	 * this sets the rulesArea as a JTextArea
	 */
	private JTextArea rulesArea;
	/**
	 * This sets the rules label as a JLabel
	 */
	private JLabel rulesLabel;
	/**
	 * this sets the rankingLabel as a JLabel
	 */
	private JLabel rankingLabel;
	/**
	 * this sets the title Label as a JLabel
	 */
	private JLabel titleLabel;
	/**
	 * this sets the gameCountLabel as a JLabel.
	 */
	private JLabel gameCountLabel;
	/**
	 * this sets the baseLayout as a SpringLayout
	 */
	private SpringLayout baseLayout;
	
	/**
	 * this constructs all of our variables and methods for the class
	 * @param baseController
	 */
	public IOPanel(IOController baseController)
	{
		this.baseController = baseController;

		saveButton = new JButton("save the game stuff");
		loadButton = new JButton("load the gamestuff");
		titleField = new JTextField(15);
		titleLabel = new JLabel("Game Title");
		rankingField = new JTextField(5);
		rankingLabel = new JLabel("game Ranking: ");
		rulesArea = new JTextArea(5, 20);
		rulesLabel = new JLabel("Game Rules: ");
		gameCountLabel = new JLabel("current game count:");
		baseLayout = new SpringLayout();

		

		setupPanel();
		setupLayout();
		setupListeners();
	}

	/**
	 * this adds the fields, layout, area, and buttons to set up the panel.
	 */
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.GREEN);
		this.add(loadButton);
		this.add(rankingField);
		this.add(rankingLabel);
		this.add(rulesArea);
		this.add(rulesLabel);
		this.add(saveButton);
		this.add(titleField);
		this.add(titleLabel);
		this.add(gameCountLabel);

	}
	
	/**
	 * this sets up action listeners for the two buttons that are used in this program and checks if the input is correct. It also keeps track of the gameCountLabel.
	 */
	private void setupListeners()
	{
		saveButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.makeGameFromInput(titleField.getText(), rankingField.getText(), rulesArea.getText());
						
					if (tempGame!= null)
					{
						baseController.saveGameInformation(tempGame);
						gameCountLabel.setText("Current game count: " + baseController.getProjectGames().size());
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Try again with a valid number");
					}
			}
		});
		
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.readGameInformation();
				if(tempGame != null)
				{
					titleField.setText(tempGame.getGameTitle());
					rankingField.setText(Integer.toString(tempGame.getFunRanking()));
					String temp = "";
					for(String currentRule : tempGame.getGameRules())
					{
						temp += currentRule + "\n";
					}
					rulesArea.setText(temp);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Check the save file make sure it isin order.");
				}
			}
		});
	}

	/**
	 * this sets up the layout of all of the fields, layout, area, and buttons
	 * in the panel.
	 */
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, gameCountLabel, 0, SpringLayout.WEST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.SOUTH, gameCountLabel, -6, SpringLayout.NORTH, titleField);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, saveButton);
		baseLayout.putConstraint(SpringLayout.EAST, loadButton, -6, SpringLayout.WEST, saveButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, rulesLabel, -110, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesArea, 0, SpringLayout.NORTH, rulesLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rulesArea, 6, SpringLayout.EAST, rulesLabel);
		baseLayout.putConstraint(SpringLayout.WEST, titleField, 27, SpringLayout.EAST, titleLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingField, -3, SpringLayout.NORTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rankingField, 6, SpringLayout.EAST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingLabel, 8, SpringLayout.SOUTH, titleLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rankingLabel, 0, SpringLayout.WEST, rulesLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, titleLabel, 3, SpringLayout.NORTH, titleField);
		baseLayout.putConstraint(SpringLayout.WEST, titleLabel, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, titleField, 109, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, rulesLabel, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, saveButton, -10, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, saveButton, -27, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesArea, -5, SpringLayout.NORTH, rulesLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rulesArea, 6, SpringLayout.EAST, rulesLabel);

	}
}
