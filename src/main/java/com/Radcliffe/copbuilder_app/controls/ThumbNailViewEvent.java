package com.Radcliffe.copbuilder_app.controls;

import java.util.EventObject;
import java.awt.image.BufferedImage;
import com.Radcliffe.copbuilder_app.AppImages;

public class ThumbNailViewEvent extends EventObject{
	private ThumbNailButtons buttons;
	private BufferedImage image;
	private String imgFile;
	private AppImages appImages;
	private int imageIndex;
	public ThumbNailViewEvent(Object source, ThumbNailButtons tnb, BufferedImage img){
		super(source);
		buttons = tnb;
		image =img;
	}
	public ThumbNailViewEvent(Object source, ThumbNailButtons tnb){
		super(source);
		buttons = tnb;
	
	}
	public ThumbNailViewEvent(Object source, ThumbNailButtons tnb, String file, int Index){
		super(source);
		buttons = tnb;
		imgFile =file;
	}
	
	
	
	public ThumbNailButtons getButtonPressed(){
		return buttons;
	}
	public BufferedImage getImage(int index){
		return image;
	}
	public String getImage(){
		return imgFile;
	}

	public String getFileName(){
		return imgFile;
	}
	public int getIndex(){
		return imageIndex;
	}
}