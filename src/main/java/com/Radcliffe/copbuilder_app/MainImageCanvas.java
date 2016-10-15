package com.Radcliffe.copbuilder_app;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerListener;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

import static java.lang.Math.*;


public class MainImageCanvas extends Canvas implements ComponentListener, ContainerListener{
	Border lineBorder;
	PictureFrame picFrame;
	private BufferedImage bi;
	private Image img;
	private Image bkGroundImg;
	
	
	public MainImageCanvas(){
		
		this.addComponentListener(this);
		this.addContainerListener(this);
		picFrame = new PictureFrame();
		setLayout(null);
		add(picFrame);
		repaint();
	}

	public void setImage(BufferedImage img){
		picFrame.setPicture(img);
		
		
	}
	public void setImage(String img){
		picFrame.setPicture(img);
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	private Image drawBackground()throws IOException{
		File fl = new File("src/main/resources/background.png");
		
		bkGroundImg = ImageIO.read(fl);
		//prepare graphics output
		bi = new BufferedImage(this.getWidth() ,this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g= bi.createGraphics();
		// Draw the background image with the canvas' width and height. Draw onto bi (buffer)
		
		g.drawImage(bkGroundImg, 0, 0,this.getWidth(),this.getHeight(), this);
		return bi;
	}
	
	@Override
	public void Draw(Graphics2D g) {
	
		
		// TODO Auto-generated method stub
		
		try{
		
		g.drawImage(drawBackground(), 0, 0,null);
		}catch (IOException ioe){
			ioe.printStackTrace();
		}
		
		picFrame.paintComponent(g);;
		g.dispose();		
		
		
		
	}

	
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
		Rectangle rect = new Rectangle();
		rect.width= this.getWidth()-200;
		rect.height=this.getHeight();
		rect.x=50;
		rect.y=1;
		picFrame.setBounds(rect);
		
	}

	
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
	}

	
	public void componentAdded(ContainerEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

	
	public void componentRemoved(ContainerEvent e) {
		// TODO Auto-generated method stub
		
	}

}
