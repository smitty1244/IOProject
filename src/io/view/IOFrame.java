package io.view;

import io.controller.IOController;

import javax.swing.JFrame;

/**
 * Frame for the IO project
 * @author Jacob Smith
 *@version 1.1 13/12/2013 Created setup method and constructor, added a title to the window
 */
public class IOFrame extends JFrame
{
	private IOController baseController;
	/**
	 * IO panel for the frame.
	 */
	private IOPanel basePanel;
	
	/**
	 * Constructor for the IOFrame
	 * @param baseController IOController passed in for MVC relationship.
	 */
	public IOFrame(IOController baseController)
	{
		basePanel = new IOPanel(baseController);
		setupFrame();
	}
	
	/**
	 * Sets up the frame size and loads the content panel.
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setTitle("IO Project");
		this.setSize(400, 400);
		this.setVisible(true);
	}
}
