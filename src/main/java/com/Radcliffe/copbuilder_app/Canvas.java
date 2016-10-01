package com.Radcliffe.copbuilder_app;
import javax.swing.JComponent;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
public abstract class Canvas extends JComponent implements ActionListener  {



	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Draw(g2);
		
		
	}

	public abstract void Draw(Graphics2D g);

	

	

	
}
