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
import java.lang.reflect.InvocationTargetException;
public class PictureFrame extends JPanel implements  MouseMotionListener, ComponentListener, IIOReadProgressListener {
	
	public PictureFrame(){
		
		
		backGroundFile = new File(BACKGROUNDIMG);
		imgProc = new ImageProcessor();
		imgProc.addIIOReadProgessListener(this);
		try{
			
			bkBufferImage = ImageIO.read(backGroundFile);
			
			//System.out.println("This is the starting width and height; "+img.getWidth() +",  " + img.getHeight());
			}catch(IOException ioe){
				System.out.println("Error in loading file");
			}
		
		this.addComponentListener(this);
		
	}
	
	
	protected BufferedImage loadPicture(){
	
			if(bufferImageStr == null){
				return bkBufferImage;
			}
				
			
				try{
					
					System.out.println("setting to true: " + loadingImage);
				
				bufferImage = imgProc.loadImage(new File(bufferImageStr));
				}catch(IOException ioe){
					ioe.printStackTrace();
				}

				
				
					/*
				    * Resize images.
				    * Unprocessed / unresized images will have higher width and height values 
				    */
					if((bufferImage.getWidth()>767) && (bufferImage.getHeight()>1914)){
					
					xWidth = bufferImage.getWidth() /6;
					yHeight = bufferImage.getHeight()/6;
					
					}
			
			return bufferImage;
		
		
	}

	public void setPicture(BufferedImage image){
		bufferImage=image;
		this.repaint();
	}

	public void setPicture(String image){
		this.bufferImageStr = image;
		loadImage=true;
		this.repaint();
	}
	protected void paintComponent(Graphics g){
		
		Graphics2D g2 = (Graphics2D)g;
		Draw(g2);
		
	}
	public void Draw(Graphics2D g) {
		
		int x=0;
		int y=0;
		if(loadImage==false)
			image=loadPicture();
		
		//create blank canvas for the progress counter
		if(loadImage==true){
			
			image = loadPicture();
			
			/*
			 * put progress drawing here?
			 * 
			 * 
			 * 
			 * drawProgress(g);
			 */
			
			

		}
		
		
		
		x =this.getWidth()/2;
		x = x -(xWidth/2);
		y = this.getHeight()/2;
		y =y-(yHeight/2);
		System.out.print("Drawing the image");
		g.drawImage(image, x,y,xWidth,yHeight, null);
		g.dispose();
	}
	

	
	private void drawProgress(float prog){
		
		int x = 0;
		int y=0;
		
			title1 = String.format("%2.0f",prog  );
			title1= title1 + "% Loaded";
			image = bkBufferImage;
			Graphics2D imageCanvas = image.createGraphics();
			imageCanvas.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			imageCanvas.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);		 
			 this.fontRender = imageCanvas.getFontRenderContext();
			
			 imageCanvas.setColor(new Color(255,200,60));
			 imageCanvas.fillRect(0, 0,this.getWidth(), this.getHeight());
			
			//draw a string on the canvas
			 imageCanvas.setFont(font);
			TextLayout tl = new TextLayout(title1,font,this.fontRender);
			
			//position our title center
			x = (int) ((this.getWidth()/8) - (tl.getBounds().getWidth()/8));
			y= (int) ((this.getHeight()/6) - (tl.getBounds().getHeight()/6));
			
			imageCanvas.setColor(new Color(50,50,50));
			tl.draw(imageCanvas, x+2, y+2);
			
			imageCanvas.setColor(new Color(255,255,255));
			tl.draw( imageCanvas, x, y);
			System.out.println("drawing progress" + this.loadingImage);
			
			x =this.getWidth()/2;
			x = x -(xWidth/2);
			y = this.getHeight()/2;
			y =y-(yHeight/2);
		
			//imageCanvas.drawImage(image, x,y,xWidth,yHeight, null);
			Graphics2D g = (Graphics2D)this.getGraphics();
			g.drawImage(image, x,y,xWidth,yHeight, null);

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
		this.loadingImage=false;
		System.out.println("completed");
	}


	@Override
	public void imageProgress(ImageReader arg0, float arg1) {
		// TODO Auto-generated method stub
		if(arg1>97)
			this.loadingImage=false;
		drawProgress(arg1);
		System.out.println("loading");
	
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
	BufferedImage image=null;
	private Rectangle Rect;
	private ImageProcessor imgProc;
	private Border lineBorder;
	private File backGroundFile; 
	
	private BufferedImage bufferImage;
	private BufferedImage bkBufferImage;
    private boolean loadingImage=false;
    private boolean loadImage=false;
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
