package com.Radcliffe.copbuilder_app;

import static java.lang.Math.toRadians;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.border.*;
import javax.swing.*;
import javax.imageio.event.IIOReadProgressListener;
import java.io.*;
public class PictureFrame extends JPanel implements  MouseMotionListener, ComponentListener, IIOReadProgressListener {
	
	public PictureFrame(){
		
		
		backGroundFile = new File(BACKGROUNDIMG);
		imgProc = new ImageProcessor();
		
		try{
			
			bkBufferImage = ImageIO.read(backGroundFile);
			
			//System.out.println("This is the starting width and height; "+img.getWidth() +",  " + img.getHeight());
			}catch(IOException ioe){
				System.out.println("Error in loading file");
			}
		
		this.addComponentListener(this);
		
	}
	
	
	protected BufferedImage loadPicture(){
	
		
		this.loadingImage= true;
		
		if(bufferImage!=null){
	
			if(bufferImageStr != null){
				
				try{
				imgProc.addIIOReadProgessListener(this);
				bufferImage = imgProc.loadImage(new File(bufferImageStr));
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
				bufferImageStr= null;
			}
		   /*
		    * Resize images.
		    * Unprocessed / unresized images will have higher width and height values 
		    */
			if((bufferImage.getWidth()>767) && (bufferImage.getHeight()>1914)){
			
			xWidth = bufferImage.getWidth() /6;
			yHeight = bufferImage.getHeight()/6;
			}
			}
		
		return bufferImage;
	}

	public void setPicture(BufferedImage image){
		bufferImage=image;
		this.repaint();
	}

	public void setPicture(String image){
		this.bufferImageStr = image;
		this.repaint();
	}
	protected void paintComponent(Graphics g){
		
		Graphics2D g2 = (Graphics2D)g;
		Draw(g2);
		
	}
	private void drawProgress(float progress){
		java.awt.Rectangle rect;
		java.awt.Color color;
		int cx = 0;
		int cy=0;
		
		
	
		/*
		 * Calculate the Center
		 */
		cx = this.getWidth()/2;
		cy = this.getHeight()/2;
	
		
		
		
		
		
		
	}
	public void Draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
		int x=0;
		int y=0;
		BufferedImage image=null;
		//create blank canvas for the progress counter
		image =this.bkBufferImage;
		Graphics2D imgcanvas = image.createGraphics();
	
		if( this.loadingImage==true){
			
			 imgcanvas.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			 imgcanvas.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);		 
			 this.fontRender = imgcanvas.getFontRenderContext();
			
			//    imgcanvas.setColor(new Color(255,200,60));
				imgcanvas.fillRect(1, 1,this.getWidth(), this.getHeight());
				
				//draw a string on the canvas
				imgcanvas.setFont(font);
				TextLayout tl = new TextLayout(title1,font,this.fontRender);
				
				//position our title center
				x = (int) ((this.getWidth()/2) - (tl.getBounds().getWidth()/2));
				y= (int) ((this.getHeight()/2) - (tl.getBounds().getHeight()/2));
				
				imgcanvas.setColor(new Color(50,50,50));
				tl.draw(imgcanvas, x+2, y+2);
				
				imgcanvas.setColor(new Color(255,255,255));
				tl.draw(imgcanvas, x, y);
				
				
				
		
				
			
		}else{
			
			image = loadPicture();
			
			x =this.getWidth()/2;
			x = x -(xWidth/2);
			y = this.getHeight()/2;
			y =y-(yHeight/2);
		
			//g.drawImage(image, x,y,xWidth,yHeight, null);
			
		}	
		
		g.drawImage(image, x,y,xWidth,yHeight, null);
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
	
	
	}

	
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void imageComplete(ImageReader arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void imageProgress(ImageReader arg0, float arg1) {
		// TODO Auto-generated method stub
		System.out.println("Progress: " + arg1);
		if(arg1>=100.0f){
			this.loadingImage=false;

		}
		drawProgress(arg1);
	}


	@Override
	public void imageStarted(ImageReader arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void readAborted(ImageReader arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sequenceComplete(ImageReader arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sequenceStarted(ImageReader arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void thumbnailComplete(ImageReader arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void thumbnailProgress(ImageReader arg0, float arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void thumbnailStarted(ImageReader arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}

	private Rectangle Rect;
	private ImageProcessor imgProc;
	private Border lineBorder;
	private File backGroundFile; 
	
	private BufferedImage bufferImage;
	private BufferedImage bkBufferImage;
    private boolean loadingImage=false;
   
    int px, py;
    int pw, ph;
    private TextLayout txtLayout;
    private FontRenderContext fontRender;
    Font font = new Font("Arial", Font.BOLD, 32);
    private String title1 = "Loading Picture";
 
	private String bufferImageStr;
	private int xWidth=1148;
	private int yHeight = 460;
	private String BACKGROUNDIMG ="src/main/resources/picframe.png";
	
}
