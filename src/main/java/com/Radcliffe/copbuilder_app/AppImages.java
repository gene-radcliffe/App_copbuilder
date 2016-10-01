package com.Radcliffe.copbuilder_app;
import java.awt.Image;
import java.awt.image.BufferedImage;
public class AppImages {
	
	private String file;
	private String newFileName;
	private int _exifOrientation;
	
	private final static String extension = new String(".jpg");
	
	public AppImages(String file_name){
	
		file = file_name;
	}
	
	public void setNewFileName(String file_name){
		newFileName = file_name;
	}
	
	public boolean equals(Object obj){
		if((obj instanceof AppImages)&& ((AppImages)obj).file.equals(this.file)){
			return true;
		}else{
			return false;
		}
	}
	public void exifOrientation(int value){
		_exifOrientation = value;
	}
	public int hashCode(){
		
		return file.length();
	}
	
	public String getFile(){
		return file;
	}
	
	public String toString(){
		return this.file;
	}

}


