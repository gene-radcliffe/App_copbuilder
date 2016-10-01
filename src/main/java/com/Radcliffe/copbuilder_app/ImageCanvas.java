package com.Radcliffe.copbuilder_app;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;

import java.io.*;

public class ImageCanvas extends JPanel  {
	
	private Image img;
	
	public void SetImage(String File_Path){
	
		Toolkit tKit = Toolkit.getDefaultToolkit();
		System.out.println(File_Path);
		img = tKit.getImage(File_Path) ;
		
		img = img.getScaledInstance(300, -1, Image.SCALE_SMOOTH);
		this.repaint();
		
		
	}
	
	
	
	
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(img,0, 0, this);
		
	}
	
}
