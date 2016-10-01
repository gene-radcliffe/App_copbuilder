package com.Radcliffe.copbuilder_app.controls;
import java.util.EventObject;
import java.awt.image.BufferedImage;

public class ThumbNailLoadEvent extends EventObject {
	private BufferedImage bImage;
	private String imgPath;
	public ThumbNailLoadEvent(Object arg0, BufferedImage image) {
		super(arg0);
		
		// TODO Auto-generated constructor stub
		bImage =image;
	}
	public ThumbNailLoadEvent(Object arg0, String imagePath) {
		super(arg0);
		imgPath = imagePath;
		
		// TODO Auto-generated constructor stub
	}
	public String getFile(){
		return imgPath;
	}
	

	
}
