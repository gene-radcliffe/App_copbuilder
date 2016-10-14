package com.Radcliffe.copbuilder_app.controls;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.event.EventListenerList;
import com.Radcliffe.copbuilder_app.AppImages;
import com.Radcliffe.copbuilder_app.Canvas;
import com.Radcliffe.copbuilder_app.ImageProcessor;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.swing.border.*;

import java.util.*;
import java.util.*;
import java.util.List;
/* must load the images.

* the event listeners will fire events that the main application will handle.
*these events will control the scrolling 
*
*
*/

public class ThumbNailCanvas extends Canvas implements MouseListener, MouseMotionListener {


	
	private BufferedImage bImage; 

	private int imgIndex=0;
	private Border border;
	private File[] arrayFiles;
	private String filePath;
	private List<AppImages> listFiles;
	private ImageProcessor imgProc;
	private JButton Next;
	private JButton Previous;
	private String leftButton = "src/main/resources/left.png";
	private String rightButton = "src/main/resources/right.png";
	private Image leftButtonImg, rightButtonImg;
	private Dimension canvasDim;
	protected EventListenerList listenerList = new EventListenerList();
	
	public ThumbNailCanvas(int x, int y){
		imgProc = new ImageProcessor();
		canvasDim = new Dimension(y,x);
		border= BorderFactory.createLineBorder(Color.GREEN);
		this.setBorder(border);
		this.setMinimumSize(canvasDim);
		this.setPreferredSize(canvasDim);
		this.setMaximumSize(canvasDim);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		try{
		leftButtonImg = ImageIO.read(new File(leftButton));
		rightButtonImg = ImageIO.read(new File(rightButton));
		}catch(IOException e){
			System.out.println(e.toString());
		}
		
	}
	
	public void addThumbNailListener(ThumbNailListener listener){
		listenerList.add(ThumbNailListener.class, listener);
	}
	public void removeThumbNailListener(ThumbNailListener listener){
		listenerList.remove(ThumbNailListener.class, listener);
		
	}
	
	void fireEvents(ThumbNailViewEvent event){
		Object[] listeners = listenerList.getListenerList();
		for(int indx =0; indx <listeners.length; indx=indx+2){
			
			if(listeners[indx]== ThumbNailListener.class){
				((ThumbNailListener) listeners[indx + 1]).ThumbNailListener(event);
			}
		}
	}
	void fireLoadEvent(ThumbNailLoadEvent event){
		Object[] listeners = listenerList.getListenerList();
		for(int indx =0; indx <listeners.length; indx=indx+2){
			
			if(listeners[indx]== ThumbNailListener.class){
				
				((ThumbNailListener) listeners[indx + 1]).ThumbNailLoadListener(event);;
			}
		}
	}
	
	public void loadImages(File[] files){
		arrayFiles =files;
		
	}
	public void loadImages(List<AppImages> files){
		listFiles=files;
		ThumbNailLoadEvent tnle = new ThumbNailLoadEvent(this, listFiles.get(0).getFile());
		fireLoadEvent(tnle);
	}
	public String getImageFile(int index){
		return  listFiles.get(index).toString();
	}
	public String getImageFile(){
		return filePath;
	}
	
	public BufferedImage getBufferedImage(int index){
		BufferedImage bimg;
		bimg = imgProc.loadFile(listFiles.get(index));
		filePath = listFiles.get(index).toString();
		return bimg;
	}

	

	public void setFolder(String path){
		
	}
	
	private void drawProgress(){
		
	}

	@Override
	public void Draw(Graphics2D g) {
		// TODO Auto-generated method stub
	
		g.drawImage(leftButtonImg, 0, 0, 100, 100, this);
		g.drawImage(rightButtonImg, canvasDim.width-100, 0,100, 100, this);
	
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//System.out.println(e.getX() + ",  " + e.getY());
		/*
		 * Previous
		 */
		if(e.getX() <=100 && e.getY() <=100 ){
			
			
			if(imgIndex > 0){
				imgIndex--;
				
				ThumbNailViewEvent event = new ThumbNailViewEvent(this, ThumbNailButtons.PREVIOUS, listFiles.get(imgIndex).toString(), imgIndex);
				fireEvents(event);
				
			}
			
			
			/*
			 * Next
			 */
		}else if(e.getX()>=canvasDim.width-100 && e.getY() <=100 ) {
			
			int size =0;
			if(listFiles!=null)
				size = listFiles.size();
			
			if (imgIndex < (size-1)){
				imgIndex++;
				System.out.println(imgIndex + " " + listFiles.size());
				ThumbNailViewEvent event = new ThumbNailViewEvent(this, ThumbNailButtons.NEXT, listFiles.get(imgIndex).toString(), imgIndex);
				fireEvents(event);
			}
			
		}
	
	}
	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("x and Y: "+ e.getX() + " " + e.getY());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}

