package com.Radcliffe.copbuilder_app;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import com.radcliffe.utilities.*;

import javax.imageio.event.IIOReadProgressListener;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.*;

import java.io.*;
import java.util.Iterator;


public class ImageProcessor {

	
	private File[] fl;
	private SequentialFileReader _fr;
	private FileInputStream _fis;
	
	
	
	private FileInputStream fileInputStream;
	private ImageInputStream imageInputStream;
	private BufferedImage buffImage;
	
	private IIOReadProgressListener iiOReadProgessListener;
	
	
	public ImageProcessor(){
		_fr = new SequentialFileReader();
	}
/*
	public BufferedImage getAnImage(String fileName){
		BufferedImage rtn=null;
		int index=0;
		AppImages ap;
		Iterator it = images.iterator();
		
		while(it.hasNext()){
			ap = images.get(index);
			System.out.println("is this it: " + ap.getImageOrigFileName() + ", " + fileName);
			if(ap.getImageOrigFileName().equals(fileName)){
				
				rtn=ap.getImage();
				break;
			}
			index+=1;
			it.next();
		}
		
		return rtn;
		
	}*/
	
	public BufferedImage loadFile(AppImages img){
		BufferedImage rtn=null;
		AffineTransform at = new AffineTransform();
		AffineTransformOp ato=null;
		int orientation=0;
		try{
			
			
			String str = img.getFile();
			_fr.setFileInput(new FileInputStream(str));
			/*
			 * retrieve the exif image orientation data by search 0x112 tag
			 */
			do{
				if(_fr.getInt16() == 0x112){
					_fr.getUint16();
					_fr.readInt32();
					orientation = _fr.readInt32();
					break;
				}
			}while(_fr.isEndOfFile());
			/*
			 * rotate the images according to the exif image orientation
			 */
			switch(orientation){
			case 6: 
				//rtn= ImageIO.read(new File(str));
				rtn = this.loadImage(new File(str));
				at.rotate(Math.toRadians(90), rtn.getWidth()/2, rtn.getHeight()/2);
				ato = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
				rtn = ato.filter(rtn, null);
				break;
			case 3: 
				//rtn= ImageIO.read(new File(str));
				rtn = this.loadImage(new File(str));
				at.rotate(Math.toRadians(-180), rtn.getWidth()/2, rtn.getHeight()/2);
				ato = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
				rtn = ato.filter(rtn, null);
				break;
			case 8: 
				//rtn= ImageIO.read(new File(str));
				rtn = this.loadImage(new File(str));
				at.rotate(Math.toRadians(-90), rtn.getWidth()/2, rtn.getHeight()/2);
				ato = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
				rtn = ato.filter(rtn, null);
				break;
			default:
				//rtn= ImageIO.read(new File(str));
				rtn = this.loadImage(new File(str));
				break;
			}
			
			
		
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return rtn;
	}


	/*
	public void LoadFiles(File[] files){
		BufferedImage img=null;
		
		for(File str: files){
			try{
			img = ImageIO.read(str);
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			AppImages aimg = new AppImages(img,str.toString());
			System.out.println(aimg.hashCode()+ ", " + aimg);
			images.add(aimg);
		}
		
	}*/
	
	public BufferedImage loadImage(File filePath) throws IOException{	
		File path = filePath;
		Iterator readers = ImageIO.getImageReadersBySuffix("jpg");
		fileInputStream = new FileInputStream(path);
		
		ImageReader imagereader = (ImageReader) readers.next();
		
		imageInputStream = ImageIO.createImageInputStream(fileInputStream);
		imagereader.setInput(imageInputStream, false);
		
		if(iiOReadProgessListener != null){
		imagereader.addIIOReadProgressListener(this.iiOReadProgessListener);
		}
		
		return buffImage = imagereader.read(0);
		
	}
	public void addIIOReadProgessListener(IIOReadProgressListener iiORPListener){
		iiOReadProgessListener = iiORPListener;
	}
}
